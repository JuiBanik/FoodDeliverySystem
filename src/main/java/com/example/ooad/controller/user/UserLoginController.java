package com.example.ooad.controller.user;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.User;
import com.example.ooad.model.UserModel;
import com.example.ooad.model.UserSignupModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserLoginController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginUtil userLoginUtil;

    @GetMapping("/user_login")
    public String getUserLoginPage(Model model,
                                   HttpSession session) {
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user != null) {
            return "redirect:user_home";
        }
        return "user_login";
    }

    @PostMapping("/user_login")
    public String submitUserLogin(@ModelAttribute UserModel userModel,
                                  HttpSession session,
                                  Model model) {
        System.out.println(userModel.getUsername());
        //System.out.println(userModel.getPassword());
        User user = userRepository.findByUserName(userModel.getUsername());
        if (user == null ) {
            System.out.println("No User Found");
        }
        if (user!= null && user.getPassword().equals(userModel.getPassword())) {
            model.addAttribute("username", user.getUserName());
            session.setAttribute("loggedInUser", user.getUserName());
            return "redirect:user_home";
        }
        model.addAttribute("message", "Incorrect username or password.");
        return "user_login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserSignupModel userModel, Model model)
    {
        // Checking if the username is already taken
        if (userRepository.findByUserName(userModel.getUsername()) != null) {
            model.addAttribute("message", "Username already taken!Please try a different username.");
            return  "user_login";
       }
        //create a new user entity to save
        User newUser = new User(userModel.getUsername(), userModel.getPassword(),
                userModel.getEmail(), userModel.getPhoneNumber());
        // Saving the user to the database
        User createdUser = userRepository.save(newUser);
        model.addAttribute("message", "User registration successful. Please login to continue");
        return "user_login";
    }

    @GetMapping("/user_logout")
    public String submitUserLogout(HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addAttribute("message", "Logout successful!");
        return "redirect:user_login";
    }
}
