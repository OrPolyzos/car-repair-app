package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.InvalidCredentialsException;
import com.rcodingschool.carrepair.exception.user.DuplicateAfmException;
import com.rcodingschool.carrepair.exception.user.DuplicateEmailException;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found!";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials!";
    private static final String THIS_EMAIL_ALREADY_EXISTS_MESSAGE = "This email already exists!";
    private static final String THIS_AFM_ALREADY_EXISTS_MESSAGE = "This AFM already exists!";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOne(Long userID) throws UserNotFoundException {
        User retrievedUser = userRepository.findOne(userID);
        if (retrievedUser == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return retrievedUser;
    }

    @Override
    public User login(String username, String password) {
        User retrievedUser;
        retrievedUser = userRepository.findByAfmAndPassword(username, password);
        if (retrievedUser == null) {
            retrievedUser = userRepository.findByEmailAndPassword(username, password);
            if (retrievedUser == null) {
                throw new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
            }
        }
        return retrievedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByAfm(String afm) {
        return userRepository.findByAfm(afm);
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) throws DuplicateEmailException, DuplicateAfmException {
        //TODO SPAGHETTI - SHOULD BE REFACTORED
        List<User> byEmailList = userRepository.findByEmail(user.getEmail());
        List<User> byAfmList = userRepository.findByAfm(user.getAfm());
        if (byAfmList.isEmpty() && byEmailList.isEmpty()) {
            userRepository.save(user);
            return;
        }
        if (!byAfmList.isEmpty()) {
            if ((byAfmList.get(0).getUserID().equals(user.getUserID()))) {
                if (!byEmailList.isEmpty()) {
                    if ((byEmailList.get(0).getUserID().equals(user.getUserID()))) {
                        userRepository.save(user);
                    } else {
                        throw new DuplicateEmailException(THIS_EMAIL_ALREADY_EXISTS_MESSAGE);
                    }
                } else {
                    throw new DuplicateAfmException(THIS_AFM_ALREADY_EXISTS_MESSAGE);
                }
            } else {
                throw new DuplicateAfmException(UserServiceImpl.THIS_AFM_ALREADY_EXISTS_MESSAGE);
            }
        }
        if (!byEmailList.isEmpty()) {
            if ((byEmailList.get(0).getUserID().equals(user.getUserID()))) {
                if (!byAfmList.isEmpty()) {
                    if ((byAfmList.get(0).getUserID().equals(user.getUserID()))) {
                        userRepository.save(user);
                    } else {
                        throw new DuplicateEmailException(UserServiceImpl.THIS_EMAIL_ALREADY_EXISTS_MESSAGE);
                    }
                } else {
                    userRepository.save(user);
                }
            } else {
                throw new DuplicateEmailException(THIS_EMAIL_ALREADY_EXISTS_MESSAGE);
            }
        }
    }

    @Override
    public void deleteByUserID(Long userID) {
        userRepository.deleteByUserID(userID);
    }
}
