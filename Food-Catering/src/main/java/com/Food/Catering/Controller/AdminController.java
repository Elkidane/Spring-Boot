package com.Food.Catering.Controller;

import com.Food.Catering.Model.User;
import com.Food.Catering.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/approve/{id}")
    public String approveCaterer(@PathVariable Long id){
        return adminService.approveCaterer(id);
    }
    @GetMapping("/users")
    public List<User> getAllUser(){
        return adminService.getAllUsers();
    }

    @PostMapping("/block/{id}")
    public String blockUser(@PathVariable Long id){
        return adminService.blockUser(id);
    }

    @PostMapping("/unblock/{id}")
    public String unblockUser(@PathVariable Long id){
        return adminService.unblockUser(id);
    }
}
