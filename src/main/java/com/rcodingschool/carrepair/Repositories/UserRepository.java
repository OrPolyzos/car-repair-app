package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOne(Long userID);
    List<User> findAll();
    List<User> findByAfm(String afm);
    List<User> findByEmail(String email);
    User save(User user);
    void deleteByUserID(Long userID);
}
