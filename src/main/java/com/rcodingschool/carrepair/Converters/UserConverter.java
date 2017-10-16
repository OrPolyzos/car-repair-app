package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;

public class UserConverter {

    public static User buildUserObject(UserForm userForm) {
        return new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAfm(), userForm.getPassword(), userForm.getEmail(), userForm.getType());
    }

    public static User buildFullUserObject(UserForm userForm) {
        return new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAfm(), userForm.getPassword(), userForm.getEmail(), userForm.getType(), userForm.getUserID(), userForm.getAddressID());
    }

    public static UserForm buildUserFormObject(User user) {
        UserForm userForm = new UserForm();
        userForm.setUserID(user.getUserID());
        userForm.setAddressID(user.getAddressID());
        userForm.setPassword(user.getPassword());
        userForm.setAfm(user.getAfm());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());
        return userForm;
    }
}
