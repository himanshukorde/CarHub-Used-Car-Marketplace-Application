package com.carInventory.repository;

import com.carInventory.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsRepository extends JpaRepository<Cars, Long> {
    @Query("SELECT c FROM Cars c " +
            "JOIN c.brand b " +
            "JOIN c.transmission t " +
            "JOIN c.model m " +
            "JOIN c.year y " +
            "JOIN c.fuelType f " +
            "WHERE b.name LIKE %:details% " +
            "OR t.transmission LIKE %:details% " +
            "OR m.name LIKE %:details% " +
            "OR f.fuelType LIKE %:details% " +
            "OR CAST(y.year AS string) LIKE %:details%")
    List<Cars> searchCar(@Param("details") String details);
}