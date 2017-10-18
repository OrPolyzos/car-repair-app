package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Address;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Exceptions.InvalidCredentialsException;
import com.rcodingschool.carrepair.Repositories.AddressRepository;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import com.rcodingschool.carrepair.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private Set<Long> loggedInUsers = new HashSet<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public User findOne(Long userID) {
        return userRepository.findOne(userID);
    }

    @Override
    public User login(String username, String password) {
        User retrievedUser;
        retrievedUser = userRepository.findByAfmAndPassword(username, password);
        if (retrievedUser == null) {
            retrievedUser = userRepository.findByEmailAndPassword(username, password);
            if (retrievedUser == null){
                throw new InvalidCredentialsException("User not found!");
            }
        }
        loggedInUsers.add(retrievedUser.getUserID());
        return retrievedUser;
    }

    @Override
    public void logout(Long userID) {
        loggedInUsers.remove(userID);
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
    public void save(User user) {
        Address address = addressRepository.save(user.getAddress());
        user.setAddressID(address.getAddressID());
        userRepository.save(user);
    }

    @Override
    public void deleteByUserID(Long userID) {
//        for (Vehicle vehicle : userRepository.findOne(userID).getUserVehicles()){
//            vehicleRepository.deleteByVehicleID(vehicle.getVehicleID());
//        }
        userRepository.deleteByUserID(userID);
    }
}
