package com.carInventory.repository;

import com.carInventory.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  Optional<Brand> findByNameIgnoreCase(String name);

  boolean existsByName(String name);

  Optional<Brand> findByName(String brand);
}