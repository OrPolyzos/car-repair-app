package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Parts")
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partID", nullable = false)
    private Long partID;

    @Column(name = "partName", nullable = false)
    private String partName;

    @Column(name = "PartPrice", nullable = false)
    private Long partPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "part", targetEntity = RepairPart.class)
    private List<RepairPart> repairParts;

    public Part() {
    }

    public Long getPartID() {
        return partID;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Long getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(Long partPrice) {
        this.partPrice = partPrice;
    }
}
