package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.Address;
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

        Address address = new Address();
        address.setAddressStreet(userForm.getAddressStreet());
        address.setAddressNumber(userForm.getAddressNumber());
        address.setAddressZipCode(userForm.getAddressZipCode());
        user.setAddress(address);
        return user;
    }

    public static User buildUpdateUserObject(UserForm userForm) {
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setAfm(userForm.getAfm());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
        user.setAddressID(userForm.getAddressID());
        user.setUserID(userForm.getUserID());

        Address address = new Address();
        address.setAddressID(userForm.getAddressID());
        address.setAddressStreet(userForm.getAddressStreet());
        address.setAddressNumber(userForm.getAddressNumber());
        address.setAddressZipCode(userForm.getAddressZipCode());

        user.setAddress(address);
        return user;
    }

    public static UserForm buildUserFormObject(User user) {
        UserForm userForm = new UserForm();
        //User Stuff
        userForm.setUserID(user.getUserID());
        userForm.setAddressID(user.getAddressID());
        userForm.setPassword(user.getPassword());
        userForm.setAfm(user.getAfm());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());

        //Address Stuff
        userForm.setAddressStreet(user.getAddress().getAddressStreet());
        userForm.setAddressNumber(user.getAddress().getAddressNumber());
        userForm.setAddressZipCode(user.getAddress().getAddressZipCode());
        return userForm;
    }
}
