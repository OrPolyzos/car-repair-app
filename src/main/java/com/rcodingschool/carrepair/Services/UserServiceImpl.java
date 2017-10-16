package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long userID) { return userRepository.findOne(userID); }

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
    public void save(User user) { userRepository.save(user); }

    @Override
    public void deleteByUserID(Long userID) {
        userRepository.deleteByUserID(userID);
    }
}
