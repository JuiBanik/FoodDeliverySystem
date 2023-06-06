package com.example.ooad.controller.admin;
import com.example.ooad.controller.admin.action.AdminAction;
import com.example.ooad.controller.admin.action.AdminActionFactory;
import com.example.ooad.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class ManageMenuController {

    @Autowired
    private AdminActionFactory adminActionFactory;

    @GetMapping("/admin_manage_menu")
    public String getAdminManageMenuPage(HttpSession httpSession,
                                    Model model) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/admin_manage_menu"; //name of view/html page to be loaded
    }
    @PostMapping(value="/admin_manage_menu")
    public String doExecute(@RequestParam("action") String action, Model model,
                            @ModelAttribute(value="userModel") UserModel userModel,
                            HttpSession httpSession) {
        System.out.println("Action name is: %s".format(action));
        /**
        if ("Add a new Menu".equals(action)) {
            System.out.println("add a new menu");
            return "redirect:add_menu";
        }
        if ("Add Item".equals(action)) {
            System.out.println("add a new item");
            return "redirect:add_item";
        }
        if ("Edit Item".equals(action)) {
            System.out.println("edit item");
            return "redirect:edit_item";
        }
        if ("Delete Item".equals(action)) {
            System.out.println("delete an item");
            return "redirect:delete_item";
        }**/
        //Replaced by Factory pattern usage
        AdminAction adminAction = adminActionFactory.getAdminAction(action);
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return adminAction.performAction(model, userModel);
    }
}

