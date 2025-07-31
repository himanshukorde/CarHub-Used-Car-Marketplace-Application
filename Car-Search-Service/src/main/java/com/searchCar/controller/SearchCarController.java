package com.searchCar.controller;

import com.searchCar.client.InventoryClient;
import com.searchCar.payload.CarResponseDTO;
import com.searchCar.service.CarSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchCarController {

    @Autowired
    private CarSearchService carSearchService;

    @GetMapping("/search-car")
    public ResponseEntity<List<CarResponseDTO>> searchCar(@RequestParam String details){
        List<CarResponseDTO> carResponseDTOS = carSearchService.searchCar(details);
        return new ResponseEntity<>(carResponseDTOS, HttpStatus.OK);
    }
}
