package com.rcodingschool.carrepair.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Users")
public class User implements Serializable {

    @Id
    @Column(name = "UserID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "Firstname", nullable = false)
    private String firstName;

    @Column(name = "Lastname", nullable = false)
    private String lastName;

    @Column(name = "Afm", nullable = false, unique = true)
    private String afm;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Type", nullable = false)
    private String type = "User";

    @Column(name = "AddressStreet", nullable = false)
    private String addressStreet;

    @Column(name = "AddressNumber", nullable = false)
    private String addressNumber;

    @Column(name = "AddressZipCode", nullable = false)
    private String addressZipCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Vehicle.class)
    private List<Vehicle> vehicles;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}