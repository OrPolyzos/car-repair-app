package com.rcodingschool.carrepair.Domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Vehicles")
public class Vehicle implements Serializable {

    @Id
    @Column(name = "VehicleID", nullable = false)
    private String vehicleID;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Model", nullable = false)
    private String model;

    @Column(name = "FuelType", nullable = false)
    private String fuelType;

    @Column(name = "Year", nullable = false)
    private String year;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "UserID", nullable = false)
    private Long userID;

    public Vehicle() {
    }

    public Vehicle(String vehicleID, String brand, String model, String fuelType, String year, String color, Long userID) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.year = year;
        this.color = color;
        this.userID = userID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
