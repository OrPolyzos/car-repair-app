package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.model.UserForm;

public class UserConverter {

    public static User buildInsertUserObject(UserForm userForm) {
        return provideBasicUser(userForm);
    }

    public static User buildUpdateUserObject(UserForm userForm) {
        User user = provideBasicUser(userForm);
        user.setUserID(userForm.getUserID());
        return user;
    }

    private static User provideBasicUser(UserForm userForm) {
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
