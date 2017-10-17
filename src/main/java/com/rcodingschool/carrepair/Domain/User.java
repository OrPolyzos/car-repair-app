package com.rcodingschool.carrepair.Domain;

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

    @Column(name = "AddressID")
    private Long addressID;

    @OneToOne(optional = false)
    @JoinColumn(name = "AddressID", referencedColumnName = "AddressID", updatable = false, insertable = false)
    private Address address;

    @OneToMany(mappedBy = "user", targetEntity = Vehicle.class)
    private List<Vehicle> userVehicles;

    public User() {
    }

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

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Vehicle> getUserVehicles() {
        return userVehicles;
    }

    public void setUserVehicles(List<Vehicle> userVehicles) {
        this.userVehicles = userVehicles;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID ='" + userID + '\'' +
                ", AddressID =" + addressID + '\'' +
                ", Email='" + email + '\'' +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Password='" + password + '\'' +
                ", Type='" + type + '\'' +
                '}';
    }

}