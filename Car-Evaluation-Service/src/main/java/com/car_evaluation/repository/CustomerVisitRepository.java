package com.car_evaluation.repository;

import com.car_evaluation.entity.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {
}