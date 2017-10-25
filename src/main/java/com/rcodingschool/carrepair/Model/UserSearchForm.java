package com.rcodingschool.carrepair.Model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSearchForm {

    @Size(min = 9, max = 9, message = "AFM should be exactly 9 digits!")
    @Pattern(regexp = "^[0-9]{9}", message = "AFM should contain only digits!")
    private String afm;

    @Size(max = 128, message = "The Email should be up to 128 characters!")
    @Email(message = "Not a valid Email address!")
    private String email;

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
