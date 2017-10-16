package com.rcodingschool.carrepair.Model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

public class UserForm {

    private Long userID;

    private Long addressID;


    @NotNull(message="This field is required!")
    @Size(min=1, max=128, message="Maximum length is 128 characters!")
    @Pattern(regexp="^[a-zA-Z]{1,128}", message="Only uppercase and lowercase characters allowed!")
    private String firstName, lastName;

    @NotNull(message="This field is required!")
    @Size(min=9, max=9, message="AFM should be exactly 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="AFM should contain only digits!")
    private String afm;

    @NotNull(message="This field is required!")
    @Size(min=6, max=16, message="The password should contain 6-16 alphanumerical characters!")
    @Pattern(regexp="^[0-9a-zA-Z]{6,16}", message="Your password can contain only alphanumerical characters!")
    private String password;

    @NotNull(message="This field is required!")
    @Size(max=128, message="The Email should be up to 128 characters!")
    @Email(message="Not a valid Email address!")
    private String email;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^(Admin|User)", message="Can only be User or Admin!")
    private String type = "User";

    @NotNull(message="This field is required!")
    @Size(max=128, message="The street name can contain up to 128 characters!")
    @Pattern(regexp="^[a-zA-Z' ]{1,128}", message="The street name can contain only characters!")
    private String addressStreet;


    @NotNull(message="This field is required!")
    @Min(value=1, message="The address number must be greater or equal than 1!")
    @Max(value=999, message="The address number must be less or equal than 999!")
    private Integer addressNumber;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^[0-9]{5}", message="The street name can contain only characters!")
    private String addressZipCode;


    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressStreet() {

        return addressStreet;
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

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
