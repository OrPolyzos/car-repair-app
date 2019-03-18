package com.rcodingschool.carrepair.domain;


import com.rcodingschool.carrepair.domain.base.ResourcePersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Repairs")
public class Repair implements Serializable, ResourcePersistable<Long> {

    @Id
    @Column(name = "RepairID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RepairDateTime", nullable = false)
    private LocalDateTime repairDateTime;

    @Column(name = "RepairStatus", nullable = false)
    private String repairStatus;

    @Column(name = "RepairTasks", nullable = false)
    private String repairTasks;

    @Column(name = "RepairTotalCost", nullable = false)
    private Long repairTotalCost;

    @Column(name = "RepairTypeID", nullable = false)
    private Short repairTypeID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "RepairTypeID", referencedColumnName = "RepairTypeID", updatable = false, insertable = false)
    private RepairType repairType;

    @Column(name = "VehicleID", nullable = false)
    private String vehicleID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "VehicleID", referencedColumnName = "VehicleID", updatable = false, insertable = false)
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repair", targetEntity = RepairPart.class)
    private List<RepairPart> repairParts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRepairTotalCost() {
        return repairTotalCost;
    }

    public void setRepairTotalCost(Long repairTotalCost) {
        this.repairTotalCost = repairTotalCost;
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<RepairPart> getRepairParts() {
        return repairParts;
    }

    public void setRepairParts(List<RepairPart> repairParts) {
        this.repairParts = repairParts;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", repairDateTime=" + repairDateTime +
                ", repairStatus='" + repairStatus + '\'' +
                ", repairTasks='" + repairTasks + '\'' +
                ", repairTotalCost=" + repairTotalCost +
                ", repairTypeID=" + repairTypeID +
                ", repairType=" + repairType +
                ", vehicleID='" + vehicleID + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}