package com.example.cardealer.entities.car;

import java.math.BigInteger;

public class CarImportDto {
    private String make;
    private String model;
    private long travelledDistance;

    public CarImportDto() {
    }

    public CarImportDto(String make, String model, long travelledDistance) {
        this();
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
