package com.rcodingschool.carrepair.Model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    private Long userID;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z]{1,128}", message = "Only uppercase and lowercase characters allowed!")
    private String firstName, lastName;

    @NotNull(message = "This field is required!")
    @Size(min = 9, max = 9, message = "The AFM should be exactly 9 digits!")
    @Pattern(regexp = "^[0-9]{9}", message = "The AFM must contain only digits!")
    private String afm;

    @NotNull(message = "This field is required!")
    @Size(min = 6, max = 16, message = "The password must be 6-16 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9@#$%^&]{6,16}", message = "The password can contain alphanumerical characters and @#$%^&!")
    private String password;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Email(message = "Not a valid Email address!")
    private String email;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^(Admin|User)", message = "Can only be User or Admin!")
    private String type = "User";

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z' ]{1,128}", message = "The street name can contain only characters!")
    private String addressStreet;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[1-9]|[1-9][0-9]{1,2}", message = "The address number can be up to 999!")
    private String addressNumber;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[0-9]{5}", message = "The Zip code can be up to 5 digits!")
    private String addressZipCode;

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
}
