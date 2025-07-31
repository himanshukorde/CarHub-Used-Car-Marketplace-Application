package com.car_evaluation.controller;

import com.car_evaluation.payload.APIResponse;
import com.car_evaluation.payload.CarInfoDto;
import com.car_evaluation.service.DetailedCarEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/evaluation")
public class CarEvaluationDetails {

    @Autowired
    private DetailedCarEvaluation detailedCarEvaluation;

    @PostMapping("/add-details")
    public ResponseEntity<APIResponse<String>> carDetails(@RequestBody CarInfoDto carInfo){
        APIResponse<String> response = detailedCarEvaluation.carDetails(carInfo);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
}
