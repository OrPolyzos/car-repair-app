package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Addresses")
public class Address implements Serializable {

    @Id
    @Column(name = "AddressID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @Column(name = "AddressStreet", nullable = false)
    private String addressStreet;

    @Column(name = "AddressNumber", nullable = false)
    private String addressNumber;

    @Column(name = "AddressZipCode", nullable = false)
    private String addressZipCode;

    @OneToOne(optional=false, mappedBy="address", targetEntity = User.class)
    private User user;

    public Address() {
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

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
