package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAfm(String afm);

    Optional<User> findByEmail(String email);

    Optional<User> findByAfmOrEmailAndPassword(String afm, String email, String password);

    Optional<User> findByAfmAndIdNot(String afm, Long userId);

    Optional<User> findByEmailAndIdNot(String afm, Long userId);

}
