package com.example.ooad.controller.admin.action;

import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public class EditItemAction implements AdminAction{
    @Override
    public String performAction(Model model, UserModel userModel) {
        System.out.println("edit item");
        return "redirect:edit_item";
    }
}
