package com.rcodingschool.carrepair.domain;


import ore.spring.web.initializr.domain.ResourcePersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Parts")
public class Part implements Serializable, ResourcePersistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partID", nullable = false)
    private Long id;

    @Column(name = "partName", nullable = false)
    private String partName;

    @Column(name = "PartPrice", nullable = false)
    private Long partPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "part", targetEntity = RepairPart.class)
    private List<RepairPart> repairParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<RepairPart> getRepairParts() {
        return repairParts;
    }

    public void setRepairParts(List<RepairPart> repairParts) {
        this.repairParts = repairParts;
    }

    @Override
    public Long getResourcePersistableId() {
        return id;
    }
}
