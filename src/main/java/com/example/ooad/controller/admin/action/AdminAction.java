package com.example.ooad.controller.admin.action;

import com.example.ooad.model.UserModel;
import org.springframework.ui.Model;

public interface AdminAction {
    public String performAction(Model model,
                                UserModel userModel);
}
