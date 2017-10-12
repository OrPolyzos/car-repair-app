package com.rcodingschool.carrepair.Domain;


public class User {

    private String firstName, lastName;
    private String afm;
    private String password;
    private String email;
    private String type = "User";

    public User(String firstName, String lastName, String afm, String password, String email, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.afm = afm;
        this.password = password;
        this.email = email;
        this.type = type;
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

    @Override
    public String toString() {
        return "User{" +
                "AFM=" + afm +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", Type='" + type + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }

}