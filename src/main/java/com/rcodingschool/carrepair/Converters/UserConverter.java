package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;

public class UserConverter {

    public static User buildInsertUserObject(UserForm userForm) {
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setAfm(userForm.getAfm());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
        user.setAddressStreet(userForm.getAddressStreet());
        user.setAddressNumber(userForm.getAddressNumber());
        user.setAddressZipCode(userForm.getAddressZipCode());
        return user;
    }

    public static User buildUpdateUserObject(UserForm userForm) {
        User user = new User();
        //This is what we need for the update (Primary Key)
        user.setUserID(userForm.getUserID());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setAfm(userForm.getAfm());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
        user.setAddressStreet(userForm.getAddressStreet());
        user.setAddressNumber(userForm.getAddressNumber());
        user.setAddressZipCode(userForm.getAddressZipCode());
        return user;
    }

    public static UserForm buildUserFormObject(User user) {
        UserForm userForm = new UserForm();
        //User Stuff
        userForm.setUserID(user.getUserID());
        userForm.setPassword(user.getPassword());
        userForm.setAfm(user.getAfm());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());
        //Address Stuff
        userForm.setAddressStreet(user.getAddressStreet());
        userForm.setAddressNumber(user.getAddressNumber());
        userForm.setAddressZipCode(user.getAddressZipCode());
        return userForm;
    }
}
