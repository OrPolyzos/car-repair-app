package com.rcodingschool.carrepair.Domain;

import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "RepairParts")
public class RepairPart implements Serializable {
//We need this relationship table because we have extra columns in it (Quantity)

    //this is optional -- but best practice
    @Id
    @Column(name = "RepairPartID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairPartID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RepairID", nullable = false)
    // edo isos thelei private Repair repair
    private List<Repair> repairsList;

    @ManyToOne(fetch = FetchType.LAZY)
    // edo isos thelei private Part parts
    @JoinColumn(name = "PartID", nullable = false)
    private List<Part> partsList;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;


    public RepairPart() {
    }

    public Long getRepairPartID() {
        return repairPartID;
    }

    public void setRepairPartID(Long repairPartID) {
        this.repairPartID = repairPartID;
    }

    public List<Repair> getRepairsList() {
        return repairsList;
    }

    public void setRepairsList(List<Repair> repairsList) {
        this.repairsList = repairsList;
    }

    public List<Part> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Part> partsList) {
        this.partsList = partsList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //    public Long getRepairID() {
//        return repairID;
//    }
//
//    public void setRepairID(Long repairID) {
//        this.repairID = repairID;
//    }
//
//    public Long getPartID() {
//        return partID;
//    }
//
//    public void setPartID(Long partID) {
//        this.partID = partID;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
}
