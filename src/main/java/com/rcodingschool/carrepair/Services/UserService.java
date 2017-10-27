package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Exceptions.DuplicateAFMException;
import com.rcodingschool.carrepair.Exceptions.DuplicateEmailException;
import com.rcodingschool.carrepair.Exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    User findOne(Long userID) throws UserNotFoundException;

    User login(String username, String password);

    List<User> findAll();

    List<User> findByAfm(String afm);

    List<User> findByEmail(String email);

    void save(User user) throws DuplicateEmailException, DuplicateAFMException;

    void deleteByUserID(Long userID);
}
