package com.car_evaluation.payload;

public class CarInfoDto {
    private String kmsDriven;
    private int yearOfManufacturing;
    private Long customerVisitId;

    public String getKmsDriven() {
        return kmsDriven;
    }

    public void setKmsDriven(String kmsDriven) {
        this.kmsDriven = kmsDriven;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public Long getCustomerVisitId() {
        return customerVisitId;
    }

    public void setCustomerVisitId(Long customerVisitId) {
        this.customerVisitId = customerVisitId;
    }
}
