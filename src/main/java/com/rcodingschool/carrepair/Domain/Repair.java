package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity(name = "Repairs")
public class Repair implements Serializable {

    @Id
    @Column(name = "RepairID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairID;

    @Column(name = "RepairDate", nullable = false)
    private LocalDate repairDate;

    @Column(name = "RepairTime")
    private LocalTime repairTime;

    @Column(name = "RepairStatus", nullable = false)
    private String repairStatus;

    @Column(name = "RepairTasks", nullable = false)
    private String repairTasks;

    @Column(name = "RepairTotalCost", nullable = false)
    private Integer repairTotalCost;

    @Column(name = "RepairTypeID", nullable = false)
    private Short repairTypeID;

    @ManyToOne(optional=false)
    @JoinColumn(name="RepairTypeID",referencedColumnName="RepairTypeID", updatable = false, insertable = false)
    private RepairType repairType;

    @Column(name = "VehicleID", nullable = false)
    private String vehicleID;

    @ManyToOne(optional=false)
    @JoinColumn(name="VehicleID",referencedColumnName="VehicleID", updatable = false, insertable = false)
    private Vehicle vehicle;


    public Repair() {
    }

    public Repair(Long repairID, LocalDate repairDate, LocalTime repairTime, String repairStatus, String repairTasks,
                  Integer repairTotalCost, Short repairTypeID, RepairType repairType, String vehicleID, Vehicle vehicle) {
        this.repairID = repairID;
        this.repairDate = repairDate;
        this.repairTime = repairTime;
        this.repairStatus = repairStatus;
        this.repairTasks = repairTasks;
        this.repairTotalCost = repairTotalCost;
        this.repairTypeID = repairTypeID;
        this.repairType = repairType;
        this.vehicleID = vehicleID;
        this.vehicle = vehicle;
    }

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public LocalTime getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(LocalTime repairTime) {
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

    public Integer getRepairTotalCost() {
        return repairTotalCost;
    }

    public void setRepairTotalCost(Integer repairTotalCost) {
        this.repairTotalCost = repairTotalCost;
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}