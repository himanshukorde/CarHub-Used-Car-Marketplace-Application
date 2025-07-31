package com.searchCar.service;

import com.searchCar.client.InventoryClient;
import com.searchCar.payload.CarResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarSearchService {

    @Autowired
    private InventoryClient InventoryClient;

    @Cacheable(value = "car", key = "#details")
    @Retry(name = "inventoryRetry")
    @CircuitBreaker(name = "inventoryDB", fallbackMethod = "inventoryFallback")
    public List<CarResponseDTO> searchCar(String details) {
        return InventoryClient.searchCar(details);
    }

    public List<CarResponseDTO> inventoryFallback(String details, Throwable ex) {
        return Collections.emptyList();
    }
}
