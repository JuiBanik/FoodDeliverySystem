package com.example.ooad.controller.admin.action;

import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public class DefaultAction implements AdminAction {
    @Override
    public String performAction(Model model, UserModel userModel) {
        System.out.println("Falling back to Manage menu page");
        return "redirect:admin_manage_menu";
    }
}
