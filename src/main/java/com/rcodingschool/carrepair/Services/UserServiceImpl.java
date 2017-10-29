package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Exceptions.DuplicateAFMException;
import com.rcodingschool.carrepair.Exceptions.DuplicateEmailException;
import com.rcodingschool.carrepair.Exceptions.InvalidCredentialsException;
import com.rcodingschool.carrepair.Exceptions.UserNotFoundException;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import com.rcodingschool.carrepair.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public User findOne(Long userID) throws UserNotFoundException {
        User retrievedUser = userRepository.findOne(userID);
        if (retrievedUser == null){
            throw new UserNotFoundException("User not found!");
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
                throw new InvalidCredentialsException("User not found!");
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
    public void save(User user) throws DuplicateEmailException, DuplicateAFMException {
        List<User> byEmailList = userRepository.findByEmail(user.getEmail());
        List<User> byAfmList = userRepository.findByAfm(user.getAfm());
        if (byAfmList.isEmpty() && byEmailList.isEmpty()){
            userRepository.save(user);
            return;
        }
        if (!byAfmList.isEmpty())
        {
            if ((byAfmList.get(0).getUserID().equals(user.getUserID()))){
                if (!byEmailList.isEmpty()){
                    if ((byEmailList.get(0).getUserID().equals(user.getUserID()))){
                        userRepository.save(user );
                    }
                    else{
                        throw new DuplicateEmailException("This email already exists!");
                    }
                }
                else{
                    throw new DuplicateAFMException("This AFM already exists!");
                }
            }
            else{
                throw new DuplicateAFMException("This AFM already exists");
            }
        }
        if (!byEmailList.isEmpty())
        {
            if ((byEmailList.get(0).getUserID().equals(user.getUserID()))){
                if (!byAfmList.isEmpty()){
                    if ((byAfmList.get(0).getUserID().equals(user.getUserID()))){
                        userRepository.save(user);
                    }
                    else{
                        throw new DuplicateEmailException("This email already exists!");
                    }
                }
                else{
                    userRepository.save(user);
                }
            }
            else{
                throw new DuplicateEmailException("This email already exists");
            }
        }
        //userRepository.save(user);
    }

    @Override
    public void deleteByUserID(Long userID) {
        userRepository.deleteByUserID(userID);
    }
}
