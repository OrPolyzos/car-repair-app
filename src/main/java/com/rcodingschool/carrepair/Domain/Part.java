package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Parts")
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partID", nullable = false)
    private Long partID;

    @Column(name = "partName", nullable = false)
    private String partName;

    @Column(name = "PartPrice", nullable = false)
    private Integer partPrice;

    public Part() {
    }

    public Part(Long partID, String partName, Integer partPrice) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
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

    public Integer getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(Integer partPrice) {
        this.partPrice = partPrice;
    }
}
