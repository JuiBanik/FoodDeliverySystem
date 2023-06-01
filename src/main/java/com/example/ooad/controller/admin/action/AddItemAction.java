package com.example.ooad.controller.admin.action;

import com.example.ooad.controller.admin.action.AdminAction;
import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public class AddItemAction implements AdminAction {
    @Override
    public String performAction(Model model, UserModel userModel) {
        System.out.println("add a new item");
        return "redirect:add_item";
    }
}
