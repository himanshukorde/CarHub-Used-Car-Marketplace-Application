package com.carInventory.repository;

import com.carInventory.entity.CarImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImagesRepository extends JpaRepository<CarImages, Long> {
}