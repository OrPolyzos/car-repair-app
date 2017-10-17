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

    @Column(name = "RepairTIme")
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

}