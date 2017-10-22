package com.rcodingschool.carrepair.Model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.Date;

public class RepairForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long repairID;

//    @NotNull(message="This field is required!")
    @Future (message="Only the future is valid!")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date repairDate;

  //  @NotNull(message="This field is required!")
    @DateTimeFormat(pattern="HH:mm")
    private Time repairTime;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The status can not contain up to 32 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,32}", message="The status can contain only characters!")
    private String repairStatus;

    @NotNull(message="This field is required!")
    @Size(max=1024, message="The tasks can contain until 1024 characters!")
    @Pattern(regexp="^[a-zA-Z']{1,1024}", message="The tasks can contain only characters!")
    private String repairTasks;

//    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=0, message="The totalcost must be greater or equal than 0!")
    private Float repairTotalCost;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Short repairTypeID;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^[A-Z]{3}-[0-9]{4}", message="Plate number must have the format 'ABC-1234'!")
    private String repairVehicleID;

//    @NotNull(message="This field is required!")
//    @Size(max=1024, message="The repair type can contain until 1024 characters!")
//    @Pattern(regexp="^[a-zA-Z']{1,1024}", message="The repair type can contain only characters!")
//    private String repairΤype;

//    @NotNull(message="This field is required!")
//    @NumberFormat(style = NumberFormat.Style.NUMBER)
//    //check the min value (can't be 1 probably)
//    @Min(value=1, message="The fixed price must be greater or equal than 1!")
//    private Integer fixedPrice;

//    public String getRepairΤype() {
//        return repairΤype;
//    }
//
//    public void setRepairΤype(String repairΤype) {
//        this.repairΤype = repairΤype;
//    }
//
//    public Integer getFixedPrice() {
//        return fixedPrice;
//    }
//
//    public void setFixedPrice(Integer fixedPrice) {
//        this.fixedPrice = fixedPrice;
//    }

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

    public Time getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Time repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getRepairTasks() {
        return repairTasks;
    }

    public void setRepairTasks(String repairTasks) {
        this.repairTasks = repairTasks;
    }

    public Float getRepairTotalCost() {
        return repairTotalCost;
    }

    public void setRepairTotalCost(Float repairTotalCost) {
        this.repairTotalCost = repairTotalCost;
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getRepairVehicleID() {
        return repairVehicleID;
    }

    public void setRepairVehicleID(String repairVehicleID) {
        this.repairVehicleID = repairVehicleID;
    }
}
