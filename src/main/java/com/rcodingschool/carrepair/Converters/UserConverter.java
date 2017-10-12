package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Model.UserForm;

public class UserConverter {

    public static User buildUserObject(UserForm userForm) {
        User user = new User(userForm.getFirstName(),userForm.getLastName(),userForm.getAfm(),userForm.getPassword(),userForm.getEmail(),userForm.getType());
        return user;
    }
}
