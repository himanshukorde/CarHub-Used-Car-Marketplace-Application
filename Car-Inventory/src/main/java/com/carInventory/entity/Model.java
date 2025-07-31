package com.carInventory.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "model")
public class Model {
    public Model(String name){
        this.name = name;
    }
    public Model(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}