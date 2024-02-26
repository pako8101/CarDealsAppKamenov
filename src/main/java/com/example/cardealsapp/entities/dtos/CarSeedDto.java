package com.example.cardealsapp.entities.dtos;

import com.google.gson.annotations.Expose;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CarSeedDto {

    @Expose
    @Size(min = 2,max = 19)
    private String make;

    @Expose
    @Size(min = 2,max = 19)
    private String model;

    @Expose
    private Integer kilometers;
    @Expose
    @Positive
    private String registerOn;

    public CarSeedDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(String registerOn) {
        this.registerOn = registerOn;
    }
}
