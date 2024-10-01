package com.okta.mongodb.AgricultureEnterpriseApp.controller;

import com.okta.mongodb.AgricultureEnterpriseApp.model.User;
import com.okta.mongodb.AgricultureEnterpriseApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Serve registration page for GET request
    @GetMapping("register")
    public String showRegisterPage() {
        return "register"; // Return the register.jsp page
      
    }

    // Register user
    @SuppressWarnings("null")
    @PostMapping("register")
    public String registerUser(@Validated @ModelAttribute User user, Model model) {
        if (userService.checkUserExists(user.getUsername()).getBody()) {
            model.addAttribute("error", "Username is taken. Please choose a different one.");
            return "register"; // Return to registration page with error
        }
        user.setRole("ROLE_NORMAL");
        userService.addUser(user);
        model.addAttribute("success", "User registered successfully");
        return "login"; // Redirect to login page or success page
    }

    // User login
    @GetMapping("login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.checkLogin(username, password).getBody();
        if (user != null) {
            model.addAttribute("message", "Login successful");
            return "profile"; // Redirect to user profile page
        }
        model.addAttribute("error", "Invalid credentials");
        return "login"; // Return to login page with error
    }

    // Get user profile
    @GetMapping("profile")
    public String getUserProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username).getBody();
        if (user != null) {
            model.addAttribute("user", user);
            return "profile"; // Return profile view
        }
        return "error"; // Return error page if user not found
    }

    // Get all users
    @GetMapping("all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            model.addAttribute("message", "No users found");
        } else {
            model.addAttribute("users", users);
        }
        return "userList"; // Return user list view
    }
}