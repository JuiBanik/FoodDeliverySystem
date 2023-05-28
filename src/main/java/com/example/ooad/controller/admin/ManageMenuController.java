package com.example.ooad.controller.admin;
import com.example.ooad.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class ManageMenuController {

    @GetMapping("/admin_manage_menu")
    public String getAdminLoginPage(@RequestParam("username") String userName,
                                    Model model) {
        model.addAttribute("username", userName);
        return "admin/admin_manage_menu"; //name of view/html page to be loaded
    }
    @PostMapping(value="/admin_manage_menu")
    public String doExecute(@RequestParam("action") String action, Model model, @ModelAttribute(value="userModel") UserModel userModel) {
        System.out.println("Action name is: %s".format(action));
        if ("Add Menu".equals(action)) {
            System.out.println("add a new menu");
            return "redirect:add_menu";
        }
        if ("Add Item".equals(action)) {
            System.out.println("add a new item");
            return "redirect:add_item";
        }
        if ("Delete Menu".equals(action)) {
            System.out.println("delete a menu");
            return "redirect:delete_menu";
        }
        if ("Delete Item".equals(action)) {
            System.out.println("delete an item");
            return "redirect:delete_item";
        }
        System.out.println("Falling back to Manage menu page");
        return "redirect:admin_manage_menu";
    }
}

