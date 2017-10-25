package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleSearchForm {

//    @Size(max=32, message="Brand should not be up to 32 characters!")
//    @Pattern(regexp="^[0-9a-zA-Z]{1,32}", message="Brand can contain only characters")
//    private String brand;

    //    @NumberFormat(style = NumberFormat.Style.NUMBER)
//    @Min(value=1, message="The userID must be greater or equal than 1!")
//    private Long userID;
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
