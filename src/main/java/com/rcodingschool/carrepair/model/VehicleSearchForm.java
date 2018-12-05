package com.rcodingschool.carrepair.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleSearchForm {

    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number should have the format 'ABC-1234'!")
    private String vehicleID;

    @Size(min = 9, max = 9, message = "AFM should be exactly 9 digits!")
    @Pattern(regexp = "^[0-9]{9}", message = "AFM should contain only digits!")
    private String afm;

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
}
