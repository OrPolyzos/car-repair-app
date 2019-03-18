package com.rcodingschool.carrepair.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "RepairTypes")
public class RepairType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RepairTypeID", nullable = false)
    private Short repairTypeID;

    @Column(name = "RepairTypeDescription", nullable = false)
    private String repairTypeDescription;

    @Column(name = "FixedPrice", nullable = false)
    private Integer fixedPrice;

    @OneToMany(mappedBy = "repairType", targetEntity = Repair.class)
    private List<Repair> repairList;

    public RepairType() {
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getRepairTypeDescription() {
        return repairTypeDescription;
    }

    public void setRepairTypeDescription(String repairTypeDescription) {
        this.repairTypeDescription = repairTypeDescription;
    }

    public Integer getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Integer fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }
}
