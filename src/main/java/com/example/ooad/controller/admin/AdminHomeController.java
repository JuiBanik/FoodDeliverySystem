package com.example.ooad.controller.admin;
import com.example.ooad.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;


@Controller
public class AdminHomeController {
    @GetMapping("/admin_home")
    public String getAdminLoginPage(HttpSession httpSession,
                                    Model model) {
        String userName = (String) httpSession.getAttribute("username");
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, userName);
        return "admin/admin_home"; //name of view/html page to be loaded
    }

    @PostMapping(value="/admin_home")
    public String doExecute(@RequestParam("action") String action,
                            Model model,
                            @ModelAttribute(value="userModel") UserModel userModel) {
        System.out.println("Action name is: %s".format(action));
        if ("View/Manage Menu".equals(action)) {
            System.out.println("Going to show Menu page");
            return "redirect:admin_view_menu";
        }
        if ("View/Manage Orders".equals(action)) {
            System.out.println("Going to show Orders page");
            return "redirect:admin_view_orders";
        }
        System.out.println("Falling back to Admin page");
        return "admin/admin_home";
    }

}
