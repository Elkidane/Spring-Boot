package com.Food.Catering.Service;

import com.Food.Catering.DTO.UserDTO;
import com.Food.Catering.Model.User;
import com.Food.Catering.Model.UserStatus;
import com.Food.Catering.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(User user) {
        UserDTO userDTO = new UserDTO();

        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                userDTO.setDesc("UserName is already in use!");
                userDTO.setStatus(false);
            } else if (userRepository.existsByEmail(user.getEmail())) {
                userDTO.setDesc("Email is already in use");
                userDTO.setStatus(false);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                userDTO.setStatus(true);
                userDTO.setDesc("Registered successfully");
            }
        } catch (Exception e) {
            userDTO.setStatus(false);
            userDTO.setDesc("ERROR OCCURRED WHILE SAVING DATA" + e);
        }
        return userDTO;
    }


    public UserDTO login(String username, String rawPassword) {
        UserDTO userDTO = new UserDTO();

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            userDTO.setStatus(false);
            userDTO.setDesc("User not found!");
            return userDTO ;
        }

        User user = userOptional.get();


        if (user.getStatus() == UserStatus.ACTIVE) {
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                userDTO.setStatus(true);
                userDTO.setDesc("Login successful");
            } else {
                userDTO.setStatus(false);
                userDTO.setDesc("Incorrect password");
            }

            return userDTO;
        } else if (user.getStatus() == UserStatus.PENDING_APPROVAL) {
            userDTO.setStatus(false);
            userDTO.setDesc("Your request is pending approval. Please try again later.");
            return userDTO;
        } else {
            userDTO.setStatus(false);
            userDTO.setDesc("Your account is blocked.");
            return userDTO;
        }
    }
    }
