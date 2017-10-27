package com.rcodingschool.carrepair.Model;


import com.rcodingschool.carrepair.Validators.Date.FutureDateConstraint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class RepairForm {

    private Long repairID;

    @FutureDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairDateTime;

    @NotNull(message = "This field is required!")
    @Size(max = 32, message = "The status can not contain up to 32 characters!")
    @Pattern(regexp = "^[a-zA-Z ]{1,32}", message = "The status can contain only characters!")
    private String repairStatus;

    @NotNull(message = "This field is required!")
    @Size(max = 1024, message = "The tasks can contain until 1024 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9,\\r\\n ]{1,1024}", message = "The tasks can contain only characters!")
    private String repairTasks;

    @NotNull(message = "This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "The repairID must be greater or equal than 1!")
    private Short repairTypeID;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number must have the format 'ABC-1234'!")
    private String repairVehicleID;

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public LocalDateTime getRepairDateTime() {
        return repairDateTime;
    }

    public void setRepairDateTime(LocalDateTime repairDateTime) {
        this.repairDateTime = repairDateTime;
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
