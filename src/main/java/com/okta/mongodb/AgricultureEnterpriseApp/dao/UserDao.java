package com.okta.mongodb.AgricultureEnterpriseApp.dao;

import com.okta.mongodb.AgricultureEnterpriseApp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    // Method to fetch users by username
    User findByUsername(String username);

    // Method to check if a user exists by username
    boolean existsByUsername(String username);

    // Custom query to fetch a user by username and password
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(String username, String password);

    // Method to fetch all users
    @SuppressWarnings("null")
    List<User> findAll();
}
