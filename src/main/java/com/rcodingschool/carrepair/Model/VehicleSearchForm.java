package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleSearchForm {

    @Size(max=32, message="Brand should not be up to 32 characters!")
    @Pattern(regexp="^[0-9a-zA-Z]{1,32}", message="Brand can contain only characters")
    private String brand;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The userID must be greater or equal than 1!")
    private Long userID;

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getUserID() { return userID; }

    public void setUserID(Long userID) {
        this.userID = userID;

    }
}
