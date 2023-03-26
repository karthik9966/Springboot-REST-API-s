package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrEmail(String UserName,String Email);
    boolean existsByUserName(String userName);
}
