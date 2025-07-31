package com.carInventory.controller;

import com.carInventory.entity.Cars;
import com.carInventory.payload.APIResponse;
import com.carInventory.payload.AddCarDto;
import com.carInventory.payload.CarResponseDTO;
import com.carInventory.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addCar(@RequestBody AddCarDto dto){
        APIResponse<String> response = carService.addCar(dto);
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/register-vin")
    public ResponseEntity<?> registerCarByVin(@RequestParam String vin) {
        try {
            Cars savedCar = carService.registerCarFromVin(vin);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to register car from VIN: " + e.getMessage());
        }
    }
    @GetMapping("/search-car")
    public List<CarResponseDTO> searchCar(@RequestParam String details){
        return carService.searchingCars(details);
    }
}


