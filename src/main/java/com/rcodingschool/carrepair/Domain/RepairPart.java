package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "RepairParts")
public class RepairPart implements Serializable {

    @Id
    @Column(name = "RepairID", nullable = false)
    private Long repairID;


    @Column(name = "PartID", nullable = false)
    private Long partID;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    public RepairPart() {
    }

    public RepairPart(Long repairID, Long partID, Integer quantity) {
        this.repairID = repairID;
        this.partID = partID;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
