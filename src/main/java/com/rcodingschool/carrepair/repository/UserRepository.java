package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserID(Long userID);

    User findByAfmAndPassword(String afm, String password);

    User findByEmailAndPassword(String email, String password);

    Optional<User> findByAfmAndUserIDNot(String afm, Long userId);

    Optional<User> findByEmailAndUserIDNot(String afm, Long userId);

    List<User> findAll();

    Optional<User> findByAfm(String afm);

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteByUserID(Long userID);

}
