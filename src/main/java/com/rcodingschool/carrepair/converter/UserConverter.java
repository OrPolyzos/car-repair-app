package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.model.UserForm;

public class UserConverter {

    public static User userFormToUser(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getUserID());
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

    public static UserForm userToUserForm(User user) {
        UserForm userForm = new UserForm();
        userForm.setUserID(user.getId());
        userForm.setPassword(user.getPassword());
        userForm.setAfm(user.getAfm());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());
        userForm.setAddressStreet(user.getAddressStreet());
        userForm.setAddressNumber(user.getAddressNumber());
        userForm.setAddressZipCode(user.getAddressZipCode());
        return userForm;
    }
}
