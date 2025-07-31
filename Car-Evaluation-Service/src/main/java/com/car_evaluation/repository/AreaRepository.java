package com.car_evaluation.repository;

import com.car_evaluation.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByPinCode(String pinCode);
}