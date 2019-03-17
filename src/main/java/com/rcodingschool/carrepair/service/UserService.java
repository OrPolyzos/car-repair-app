package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.user.DuplicateAfmException;
import com.rcodingschool.carrepair.exception.user.DuplicateEmailException;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.model.UserSearchForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findOne(Long userID) throws UserNotFoundException;

    User findOneByAfm(String afm) throws UserNotFoundException;

    User login(String username, String password);

    List<User> findAll();

    List<User> searchUsersBy(UserSearchForm userSearchForm);

    Optional<User> findByAfm(String afm);

    Optional<User> findByEmail(String email);

    void insert(User userToSave) throws DuplicateAfmException, DuplicateEmailException;

    void update(User user) throws DuplicateEmailException, DuplicateAfmException;

    void deleteByUserID(Long userID) throws UserNotFoundException;

}
