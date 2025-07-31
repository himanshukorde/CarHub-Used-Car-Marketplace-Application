package com.car_evaluation.repository;

import com.car_evaluation.entity.CarDetailedEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailedEvaluationRepository extends JpaRepository<CarDetailedEvaluation, Long> {
}