package com.example.ooad.controller;

import com.example.ooad.jpa.entity.AdminUser;
import com.example.ooad.jpa.AdminUserRepository;
import com.example.ooad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLoginController {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @GetMapping("/admin_login")
    public String getAdminLoginPage(Model model) {
        return "admin_login";
    }

    @PostMapping("/admin_login")
    public String submitAdminLogin(@ModelAttribute UserModel userModel, Model model) {
        System.out.println(userModel.getUsername());
        System.out.println(userModel.getPassword());
        AdminUser adminUser = adminUserRepository.findByUserName(userModel.getUsername());
        if (adminUser == null ) {
            System.out.println("No user found");
        }
        if (adminUser!= null && adminUser.getPassword().equals(userModel.getPassword())) {
            model.addAttribute("username", adminUser.getUserName());
            return "admin_home";
        }
        model.addAttribute("message", "Incorrect username or password.");
        return "admin_login";
    }

}
