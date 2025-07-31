package com.car_evaluation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms_driven", nullable = false)
    private String kmsDriven;

    @Column(name = "year_of_manufacturing", nullable = false)
    private int yearOfManufacturing;

    @ManyToOne
    @JoinColumn(name = "customer_visit_id", nullable = false)
    private CustomerVisit customerVisit;


    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKmsDriven() {
        return kmsDriven;
    }

    public void setKmsDriven(String kmsDriven) {
        this.kmsDriven = kmsDriven;
    }

    public CustomerVisit getCustomerVisit() {
        return customerVisit;
    }

    public void setCustomerVisit(CustomerVisit customerVisit) {
        this.customerVisit = customerVisit;
    }
}