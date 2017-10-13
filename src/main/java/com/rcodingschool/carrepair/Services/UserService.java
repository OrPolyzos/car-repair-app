package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findByAfm(String afm);

    List<User> findByEmail(String email);

    void save(User user);

    void deleteByUserID(Long userID);
}
