package com.carInventory.service;

import com.carInventory.constants.AppConstants;
import com.carInventory.entity.*;
import com.carInventory.payload.APIResponse;
import com.carInventory.payload.AddCarDto;
import com.carInventory.payload.CarResponseDTO;
import com.carInventory.payload.EmailRequest;
import com.carInventory.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {
    private ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private FuelTypeRepository fuelTypeRepository;
    private ModelRepository modelRepository;
    private TransmissionRepository transmissionRepository;
    private CarsRepository carRepository;
    private YearRepository yearRepository;
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;
    private VINService vinService;

    public CarService(ModelMapper modelMapper, BrandRepository brandRepository, FuelTypeRepository fuelTypeRepository,
                      ModelRepository modelRepository, TransmissionRepository transmissionRepository,
                      CarsRepository carRepository,
                      YearRepository yearRepository, KafkaTemplate<String, EmailRequest> kafkaTemplate, VINService vinService) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.modelRepository = modelRepository;
        this.transmissionRepository = transmissionRepository;
        this.carRepository = carRepository;
        this.yearRepository = yearRepository;

        this.kafkaTemplate = kafkaTemplate;
        this.vinService = vinService;
    }


    public APIResponse<String> addCar(AddCarDto dto) {
//      User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Brand brand = brandRepository.findByNameIgnoreCase(dto.getBrand()).orElseGet(() -> brandRepository.save(new Brand(dto.getBrand())));
        FuelType fuelType = fuelTypeRepository.findByFuelTypeIgnoreCase(dto.getFuelType()).orElseGet(() -> fuelTypeRepository.save(new FuelType(dto.getFuelType())));
        Model model = modelRepository.findByNameIgnoreCase(dto.getModel()).orElseGet(() -> modelRepository.save(new Model(dto.getModel())));
        Transmission transmission = transmissionRepository.findByTransmissionIgnoreCase(dto.getTransmission()).orElseGet(() -> transmissionRepository.save(new Transmission(dto.getTransmission())));
        Year year = yearRepository.findByYear(dto.getYear()).orElseGet(() -> yearRepository.save(new Year(dto.getYear())));
        Cars carInfo = new Cars();
        carInfo.setBrand(brand);
        carInfo.setFuelType(fuelType);
        carInfo.setModel(model);
        carInfo.setTransmission(transmission);
        carInfo.setYear(year);
//      carInfo.setStatus(CarStatus.PENDING);
        carRepository.save(carInfo);

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Done!");
        response.setStatus(201);
        response.setData("Car Added successfully");

        EmailRequest request = new EmailRequest("himanshukorde2002@gmail.com",
                "property-added","Property added Successfully");
        kafkaTemplate.send(AppConstants.TOPIC, request);

        return response;
    }

    public Cars registerCarFromVin(String vin) {
        Map<String, String> decoded = vinService.decodeVin(vin);

        Brand brand = brandRepository.findByName(decoded.get("brand"))
                .orElseGet(() -> brandRepository.save(new Brand(decoded.get("brand"))));

        Model model = modelRepository.findByName(decoded.get("model"))
                .orElseGet(() -> modelRepository.save(new Model(decoded.get("model"))));

        FuelType fuelType = fuelTypeRepository.findByFuelType(decoded.get("fuelType"))
                .orElseGet(() -> fuelTypeRepository.save(new FuelType(decoded.get("fuelType"))));

        Transmission transmission = transmissionRepository.findByTransmission(decoded.get("transmission"))
                .orElseGet(() -> transmissionRepository.save(new Transmission(decoded.get("transmission"))));

        Year year = yearRepository.findByYear(Integer.parseInt(decoded.get("year")))
                .orElseGet(() -> yearRepository.save(new Year(Integer.parseInt(decoded.get("year")))));

        Cars car = new Cars();
        car.setBrand(brand);
        car.setModel(model);
        car.setFuelType(fuelType);
        car.setTransmission(transmission);
        car.setYear(year);

        return carRepository.save(car);
    }

    public List<CarResponseDTO> searchingCars(String details){
        List<Cars> cars = carRepository.searchCar(details);
        return cars.stream().map(car -> new CarResponseDTO(
                car.getId(),
                car.getBrand().getName(),
                car.getModel().getName(),
                car.getFuelType().getFuelType(),
                car.getTransmission().getTransmission(),
                car.getYear().getYear()
        )).collect(Collectors.toList());
    }
}