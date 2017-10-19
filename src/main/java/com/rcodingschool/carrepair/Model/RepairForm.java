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
    private Float totalCost;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Short repairTypeID;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^[A-Z]{3}-[0-9]{4}", message="Plate number must have the format 'ABC-1234'!")
    private String vehicleID;

    
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

    public Float getTotalCost() { return totalCost; }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public Short getRepairTypeID() { return repairTypeID; }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getVehicleID() { return vehicleID; }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}
