package com.carInventory.repository;

import com.carInventory.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    Optional<FuelType> findByFuelTypeIgnoreCase(String fuelType);

    Optional<FuelType> findByFuelType(String fuelType);
}