package com.Food.Catering.Service;

import com.Food.Catering.Model.Caterer;
import com.Food.Catering.Model.User;
import com.Food.Catering.Model.UserStatus;
import com.Food.Catering.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers( ){
        return userRepository.findAll();
    }


    public String approveCaterer(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user instanceof Caterer) {
                user.setStatus(UserStatus.ACTIVE);
                userRepository.save(user);
                return "Caterer approved successfully";
            } else {
                return "User is not a caterer.";
            }
        } else {
            return "Couldn't find the user";
        }
    }

    private String changeUserStatus(Long id,UserStatus status){
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(status);
            userRepository.save(user);
            return "User status updated to " + status;
        }else{
            return "User not found";
        }
    }

    public String blockUser(Long id){
        return changeUserStatus(id,UserStatus.BLOCKED);
    }
    public String unblockUser(Long id){
        return changeUserStatus(id,UserStatus.ACTIVE);
    }
}
