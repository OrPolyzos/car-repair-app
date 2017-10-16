package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartID", nullable = false)
    private Long partID;

    @Column(name = "PartName", nullable = false)
    private String name;

    @Column(name = "PartPrice", nullable = false)
    private Integer partPrice;

    public Part() {
    }

    public Part(Long partID, String name, Integer partPrice) {
        this.partID = partID;
        this.name = name;
        this.partPrice = partPrice;
    }

    public Long getPartID() {
        return partID;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(Integer partPrice) {
        this.partPrice = partPrice;
    }
}
