package com.rcodingschool.carrepair.Model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.Date;

public class RepairForm {

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long repairID;

    @NotNull(message="This field is required!")
    @Future (message="Only the future is valid!")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date repairDate;

    @NotNull(message="This field is required!")
    @DateTimeFormat(pattern="HH:mm")
    private Time repairTime;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The status can not contain up to 32 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,32}", message="The status can contain only characters!")
    private String status;

    @NotNull(message="This field is required!")
    @Size(max=1024, message="The tasks can contain until 1024 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,1024}", message="The tasks can contain only characters!")
    private String tasks;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=0, message="The totalcost must be greater or equal than 0!")
    private int totalCost;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Short repairTypeID;

    @NotNull(message="This field is required!")
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

    public Time getRepairTime() { return repairTime; }

    public void setRepairTime(Time repairTime) {
        this.repairTime = repairTime;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTasks() { return tasks; }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public int getTotalCost() { return totalCost; }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Short getRepairtTypeID() { return repairTypeID; }

    public void setRepairtTypeID(Short repairtTypeID) {
        this.repairTypeID = repairtTypeID;
    }

    public Long getVehicleID() { return vehicleID; }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }
}
