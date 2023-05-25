package com.example.ooad.controller;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.User;
import com.example.ooad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;



@Controller
public class UserLoginController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user_login")
    public String getUserLoginPage(Model model) {
        return "user_login";
    }

    @PostMapping("/user_login")
    public String submitUserLogin(@ModelAttribute UserModel userModel, Model model) {
        System.out.println(userModel.getUsername());
        System.out.println(userModel.getPassword());
        User user = userRepository.findByUserName(userModel.getUsername());
        if (user == null ) {
            System.out.println("No User Found");
        }
        if (user!= null && user.getPassword().equals(userModel.getPassword())) {
            model.addAttribute("username", user.getUserName());
            return "User_home";
        }
        model.addAttribute("message", "Incorrect username or password.");
        return "user_login";
    }

    @PostMapping("/signup")
    //public ResponseEntity<String> signup(@ModelAttribute User user)
    public String signup(@ModelAttribute User user, Model model)
    {
        // Checking if the username is already taken
//        if (userRepository.findByUserName(user.getUserName()) != null)
//        {
//            return ResponseEntity.badRequest().body("Username Already Exists");
//        }

        // Saving the user to the database
        userRepository.save(user);
        //return ResponseEntity.ok("User Registered Successfully");
        model.addAttribute("username", user.getUserName());
        return "User_home";
    }
}
