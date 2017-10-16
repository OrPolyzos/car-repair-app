package com.rcodingschool.carrepair.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "Addresses")
public class Address implements Serializable {

    @Id
    @Column(name = "AddressID", nullable = false)
    private Long addressID;

    @Column(name = "AddressStreet", nullable = false)
    private String addressStreet;

    @Column(name = "AddressNumber", nullable = false)
    private Integer addressNumber;

    @Column(name = "AddressZipCode", nullable = false)
    private String addressZipCode;

    public Address() {
    }

    public Address(Long addressID, String addressStreet, Integer addressNumber, String addressZipCode) {
        this.addressID = addressID;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressZipCode = addressZipCode;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }
}
