package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;

import java.util.List;

public interface UserService {

    User findOne(Long userID);

    User login(String username, String password);

    void logout(Long userID) throws Exception;

    List<User> findAll();

    List<User> findByAfm(String afm);

    List<User> findByEmail(String email);

    void save(User user);

    void deleteByUserID(Long userID);
}
