package com.rcodingschool.carrepair.Domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Repairs")
public class Repair implements Serializable {

    @Id
    @Column(name = "RepairID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairID;

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


    public Repair() {
    }

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

    @Override
    public String toString() {
        return "Repair{" +
                "repairID=" + repairID +
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