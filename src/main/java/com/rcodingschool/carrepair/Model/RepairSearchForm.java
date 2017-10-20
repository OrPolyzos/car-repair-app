package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class RepairSearchForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long repairID;

    @Future(message="Only the future is valid!")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date repairDate;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^[A-Z]{3}-[0-9]{4}", message="Plate number must have the format 'ABC-1234'!")
    private String repairVehicleID;

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getRepairVehicleID() {
        return repairVehicleID;
    }

    public void setRepairVehicleID(String repairVehicleID) {
        this.repairVehicleID = repairVehicleID;
    }
}
