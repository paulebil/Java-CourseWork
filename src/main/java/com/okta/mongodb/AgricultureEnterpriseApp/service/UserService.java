package com.okta.mongodb.AgricultureEnterpriseApp.service;

import com.okta.mongodb.AgricultureEnterpriseApp.dao.UserDao;
import com.okta.mongodb.AgricultureEnterpriseApp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // Get all users
    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Add a new user
    public ResponseEntity<String> addUser(User user) {
        try {
            userDao.save(user);
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation (e.g., username)
            e.printStackTrace();
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check login credentials
    @SuppressWarnings("null")
    public ResponseEntity<User> checkLogin(String username, String password) {
        try {
            User user = userDao.findByUsernameAndPassword(username, password);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a user exists by username
    public ResponseEntity<Boolean> checkUserExists(String username) {
        try {
            boolean exists = userDao.existsByUsername(username);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a user by username
    @SuppressWarnings("null")
    public ResponseEntity<User> getUserByUsername(String username) {
        try {
            User user = userDao.findByUsername(username);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
