package com.carInventory.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "transmission")
public class Transmission {
    public Transmission(String transmission){
        this.transmission = transmission;
    }
    public Transmission(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "transmission", length = 50)
    private String transmission;

    public String getTransmission() {
        return transmission;
    }

    public void settransmission(String type) {
        this.transmission = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}