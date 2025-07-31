package com.car_evaluation.service;

import com.car_evaluation.entity.CarDetailedEvaluation;
import com.car_evaluation.entity.CustomerVisit;
import com.car_evaluation.payload.APIResponse;
import com.car_evaluation.payload.CarInfoDto;
import com.car_evaluation.repository.CarDetailedEvaluationRepository;
import com.car_evaluation.repository.CustomerVisitRepository;
import org.springframework.stereotype.Service;

@Service
public class DetailedCarEvaluation {
    private CustomerVisitRepository customerVisitRepository;
    private final CarDetailedEvaluationRepository carDetailedEvaluationRepository;

    public DetailedCarEvaluation(CustomerVisitRepository customerVisitRepository,
                                 CarDetailedEvaluationRepository carDetailedEvaluationRepository) {
        this.customerVisitRepository = customerVisitRepository;
        this.carDetailedEvaluationRepository = carDetailedEvaluationRepository;
    }

    public APIResponse<String> carDetails(CarInfoDto carInfo){
        APIResponse<String> response = new APIResponse<>();
        CustomerVisit customer = customerVisitRepository.findById(carInfo.getCustomerVisitId())
                .orElseThrow(() -> new RuntimeException("Customer visit not found"));
        CarDetailedEvaluation car = new CarDetailedEvaluation();
        car.setKmsDriven(carInfo.getKmsDriven());
        car.setYearOfManufacturing(carInfo.getYearOfManufacturing());
        car.setCustomerVisit(customer);
        carDetailedEvaluationRepository.save(car);
        response.setData("Success");
        response.setMessage("Car Details Added Successfully");
        response.setStatus(201);
        return response;
    }
}
