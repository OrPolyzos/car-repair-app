package com.rcodingschool.carrepair.service.resource;

import com.rcodingschool.carrepair.domain.User;
import com.rcodingschool.carrepair.exception.InvalidCredentialsException;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.user.DuplicateAfmException;
import com.rcodingschool.carrepair.exception.user.DuplicateEmailException;
import com.rcodingschool.carrepair.model.UserSearchForm;
import com.rcodingschool.carrepair.repository.UserRepository;
import com.rcodingschool.carrepair.service.base.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserResourceService extends ResourceService<User, UserSearchForm, Long> {

    private final UserRepository userRepository;

    @Autowired
    public UserResourceService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User login(String afmOrEmail, String password) {
        return userRepository.findByAfmOrEmailAndPassword(afmOrEmail, afmOrEmail, password).orElseThrow(() -> new InvalidCredentialsException(afmOrEmail));
    }

    public Optional<User> findOptionalByAfm(String afm) {
        return userRepository.findByAfm(afm);
    }

    public Optional<User> findOptionalByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Iterable<User> searchBy(UserSearchForm userSearchForm) {
        if (userSearchForm.getAfm() == null && userSearchForm.getEmail() == null) {
            return findAll();
        } else if (userSearchForm.getAfm() != null) {
            return mapOptionalToList(findOptionalByAfm(userSearchForm.getAfm()));
        } else {
            return mapOptionalToList(findOptionalByEmail(userSearchForm.getEmail()));
        }
    }

    @Override
    protected void validateBeforeInsertOrThrow(User userToSave) throws ResourceException {
        if (userRepository.findByAfm(userToSave.getAfm()).isPresent()) {
            throw new DuplicateAfmException(userToSave.getAfm());
        } else if (userRepository.findByEmail(userToSave.getEmail()).isPresent()) {
            throw new DuplicateEmailException(userToSave.getEmail());
        }
    }

    @Override
    protected void validateBeforeUpdateOrThrow(User userToUpdate) throws ResourceException {
        if (userRepository.findByAfmAndIdNot(userToUpdate.getAfm(), userToUpdate.getId()).isPresent()) {
            throw new DuplicateAfmException(userToUpdate.getAfm());
        } else if (userRepository.findByEmailAndIdNot(userToUpdate.getEmail(), userToUpdate.getId()).isPresent()) {
            throw new DuplicateEmailException(userToUpdate.getEmail());
        }
    }
}
