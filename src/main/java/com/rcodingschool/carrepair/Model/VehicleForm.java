package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleForm {

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number must have the format 'ABC-1234'!")
    private String vehicleID;

    @NotNull(message = "This field is required!")
    @Size(max = 32, message = "Brand should be up to 32 characters!")
    @Pattern(regexp = "^[0-9a-zA-Z' ]{1,32}", message = "Brand can contain only alphanumericals!")
    private String brand;

    @NotNull(message = "This field is required!")
    @Size(max = 32, message = "The model should be up to 32 characters!")
    @Pattern(regexp = "^[0-9a-zA-Z' ]{1,32}", message = "The model can contain only alphanumericals!")
    private String model;

    @NotNull(message = "This field is required!")
    @Size(max = 32, message = "The color should be up to 32 characters!")
    @Pattern(regexp = "^[a-zA-Z ]{1,32}", message = "Color contains only characters!")
    private String color;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^(Petrol|Diesel|Gas)", message = "FuelType must be either Petrol or Diesel or Gas!")
    private String fuelType;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^19[5-9][0-9]|200[0-9]|201[0-7]", message = "Choose a year from 1950 to 2017!")
    private String year;

    @NotNull(message = "This field is required!")
    @Size(min = 9, max = 9, message = "AFM should be exactly 9 digits!")
    @Pattern(regexp = "^[0-9]{9}", message = "AFM should contain only digits!")
    private String afm;

    private Long userID;

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

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
