package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.Year;

public class VehicleForm {

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The vehicleID must be greater or equal than 1!")
    private Long vehicleID;

    @NotNull(message="This field is required!")
    @Size(max=32, message="Brand should not be up to 32 characters!")
    @Pattern(regexp="^[0-9a-zA-Z]{1,32}", message="Brand can contain only characters")
    private String brand;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The model should not be up to 32 characters!")
    @Pattern(regexp="^[0-9a-zA-Z']{1,32}", message="The model can contain only characters!")
    private String model;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The color should be up to 32 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,32}", message="Color contains only characters!")
    private String color;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The fuelType should be up to 32 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,32}", message="FuelType contains only characters!")
    private String fuelType;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1950, message="Year of construction must be greater or equal than 1950!")
    @Max(value=2017, message = "Year of construction must not be greater than 2017!")
    private Year year;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The userID must be greater or equal than 1!")
    private Long userID;


    public Long getVehicleID() { return vehicleID; }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() { return model; }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() { return year; }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getColor() { return color; }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() { return fuelType; }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getUserID() { return userID; }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


}
