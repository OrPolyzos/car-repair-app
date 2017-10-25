package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "RepairParts")
public class RepairPart implements Serializable {

    @Id
    @Column(name = "RepairPartID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairPartID;

    @Column(name = "RepairID", nullable = false)
    private Long repairID;

    @Column(name = "PartID", nullable = false)
    private Long partID;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RepairID", nullable = false, updatable = false, insertable = false)
    private Repair repair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PartID", nullable = false, updatable = false, insertable = false)
    private Part part;

    public RepairPart() {
    }

    public Long getRepairPartID() {
        return repairPartID;
    }

    public void setRepairPartID(Long repairPartID) {
        this.repairPartID = repairPartID;
    }

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public Long getPartID() {
        return partID;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
