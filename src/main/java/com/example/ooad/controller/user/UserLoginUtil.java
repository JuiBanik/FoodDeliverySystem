package com.example.ooad.controller.user;

import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserLoginUtil {
    private UserRepository userRepository;

    public UserLoginUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedInUser(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("loggedInUser");
        if (userName!= null && userName.isBlank()) {
            return null;
        }
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return null;
        }
        model.addAttribute("username", userName);
        return user;
    }
}
