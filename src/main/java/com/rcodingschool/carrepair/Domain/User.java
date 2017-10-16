package com.rcodingschool.carrepair.Domain;

import javax.persistence.*;
import java.io.Serializable;

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

    public User() {
    }

    public User(String firstName, String lastName, String afm, String password, String email, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.afm = afm;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public User(String firstName, String lastName, String afm, String password, String email, String type, Long userID, Long addressID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.afm = afm;
        this.password = password;
        this.email = email;
        this.type = type;
        this.userID = userID;
        this.addressID = addressID;
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
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