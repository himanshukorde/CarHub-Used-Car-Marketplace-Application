package com.car_evaluation.repository;

import com.car_evaluation.entity.CarEvaluationPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEvaluationPhotosRepository extends JpaRepository<CarEvaluationPhotos, Long> {
}