package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.util.Date;

public class RepairSearchForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long repairID;

    @Future(message="Only the future is valid!")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date repairDate;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The VehicleID must be greater or equal than 1!")
    private Long vehicleID;

    public Long getRepairID() { return repairID; }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public Date getRepairDate() { return repairDate; }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Long getVehicleID() { return vehicleID; }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

}
