package com.rcodingschool.carrepair.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", updatable = false, insertable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", targetEntity = Repair.class)
    private List<Repair> vehicleRepairs;

    public Vehicle() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Repair> getVehicleRepairs() {
        return vehicleRepairs;
    }

    public void setVehicleRepairs(List<Repair> vehicleRepairs) {
        this.vehicleRepairs = vehicleRepairs;
    }
}
