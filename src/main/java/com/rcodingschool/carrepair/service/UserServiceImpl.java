package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.InvalidCredentialsException;
import com.rcodingschool.carrepair.exception.user.DuplicateAfmException;
import com.rcodingschool.carrepair.exception.user.DuplicateEmailException;
import com.rcodingschool.carrepair.exception.user.UserNotFoundException;
import com.rcodingschool.carrepair.model.UserSearchForm;
import com.rcodingschool.carrepair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials!";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOne(Long userId) throws UserNotFoundException {
        return userRepository.findByUserID(userId).orElseThrow(() -> new UserNotFoundException(userId));
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
    public List<User> searchUsersBy(UserSearchForm userSearchForm) {
        if (userSearchForm.getAfm() == null && userSearchForm.getEmail() == null) {
            return findAll();
        } else if (userSearchForm.getAfm() != null) {
            return findByAfm(userSearchForm.getAfm());
        } else {
            return findByEmail(userSearchForm.getEmail());
        }
    }

    @Override
    public List<User> findByAfm(String afm) {
        Optional<User> retrievedUser = userRepository.findByAfm(afm);
        return retrievedUser.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    @Override
    public List<User> findByEmail(String email) {
        Optional<User> retrievedUser = userRepository.findByEmail(email);
        return retrievedUser.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    public void insert(User userToInsert) throws DuplicateAfmException, DuplicateEmailException {
        if (userRepository.findByAfm(userToInsert.getAfm()).isPresent()) {
            throw new DuplicateAfmException(userToInsert.getAfm());
        } else if (userRepository.findByEmail(userToInsert.getEmail()).isPresent()) {
            throw new DuplicateEmailException(userToInsert.getEmail());
        } else {
            userRepository.save(userToInsert);
        }
    }

    @Override
    public void update(User userToUpdate) throws DuplicateEmailException, DuplicateAfmException {
        if (userRepository.findByAfmAndUserIDNot(userToUpdate.getAfm(), userToUpdate.getUserID()).isPresent()) {
            throw new DuplicateAfmException(userToUpdate.getAfm());
        } else if (userRepository.findByEmailAndUserIDNot(userToUpdate.getEmail(), userToUpdate.getUserID()).isPresent()) {
            throw new DuplicateEmailException(userToUpdate.getEmail());
        } else {
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteByUserID(Long userID) throws UserNotFoundException {
        findOne(userID);
        userRepository.deleteByUserID(userID);
    }
}
