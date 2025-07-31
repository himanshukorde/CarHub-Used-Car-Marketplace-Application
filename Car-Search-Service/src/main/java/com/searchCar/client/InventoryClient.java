package com.searchCar.client;

import com.searchCar.payload.CarResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@FeignClient(name="Car-Inventory",url = "http://localhost:9091")
public interface InventoryClient {


    @GetMapping("/api/v1/car/search-car")
    public List<CarResponseDTO> searchCar(@RequestParam String details);

}
