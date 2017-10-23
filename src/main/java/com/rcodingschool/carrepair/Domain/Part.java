package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Parts")
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partID", nullable = false)
    private String partID;

    @Column(name = "partName", nullable = false)
    private String partName;

    @Column(name = "PartPrice", nullable = false)
    private String partPrice;

    public Part() {
    }

    public Part(String partID, String partName, String partPrice) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(String partPrice) {
        this.partPrice = partPrice;
    }
}
