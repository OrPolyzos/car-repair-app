package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class RepairSearchForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "The repairID must be greater or equal than 1!")
    private Long repairID;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairDateTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairDateTimeEnd;

    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number must have the format 'ABC-1234'!")
    private String repairVehicleID;

    public RepairSearchForm() {
    }

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public LocalDateTime getRepairDateTimeStart() {
        return repairDateTimeStart;
    }

    public void setRepairDateTimeStart(LocalDateTime repairDateTimeStart) {
        this.repairDateTimeStart = repairDateTimeStart;
    }

    public LocalDateTime getRepairDateTimeEnd() {
        return repairDateTimeEnd;
    }

    public void setRepairDateTimeEnd(LocalDateTime repairDateTimeEnd) {
        this.repairDateTimeEnd = repairDateTimeEnd;
    }

    public String getRepairVehicleID() {
        return repairVehicleID;
    }

    public void setRepairVehicleID(String repairVehicleID) {
        this.repairVehicleID = repairVehicleID;
    }
}
