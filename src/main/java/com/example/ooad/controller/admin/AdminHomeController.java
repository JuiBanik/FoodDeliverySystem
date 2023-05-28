package com.example.ooad.controller.admin;
import com.example.ooad.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminHomeController {
    @GetMapping("/admin_home")
    public String getAdminLoginPage(@RequestParam("username") String userName,
                                    Model model) {
        model.addAttribute("username", userName);
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
