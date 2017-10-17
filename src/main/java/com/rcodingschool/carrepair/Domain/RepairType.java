package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "RepairTypes")
public class RepairType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RepairTypeID", nullable = false)
    private Short repairTypeID;

    @Column(name = "RepairType", nullable = false)
    private String repairType;

    @Column(name = "FixedPrice", nullable = false)
    private Integer fixedPrice;

    @OneToMany(mappedBy = "repairType", targetEntity = Repair.class)
    private List<Repair> repairsList;

    public RepairType() {
    }

    public RepairType(Short repairTypeID, String repairType, Integer fixedPrice) {
        this.repairTypeID = repairTypeID;
        this.repairType = repairType;
        this.fixedPrice = fixedPrice;
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public Integer getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Integer fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
}
