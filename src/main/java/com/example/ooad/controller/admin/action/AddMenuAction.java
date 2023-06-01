package com.example.ooad.controller.admin.action;

import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public class AddMenuAction  implements AdminAction {
    @Override
    public String performAction(Model model, UserModel userModel) {
        System.out.println("add a new menu");
        return "redirect:add_menu";
    }
}
