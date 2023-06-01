package com.example.ooad.controller.admin.action;

import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public class DeleteItemAction implements AdminAction{
    @Override
    public String performAction(Model model, UserModel userModel) {
        System.out.println("delete an item");
        return "redirect:delete_item";
    }
}
