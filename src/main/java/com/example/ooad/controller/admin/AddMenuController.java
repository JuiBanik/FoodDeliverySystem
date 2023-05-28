package com.example.ooad.controller.admin;

import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddMenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/add_menu")
    public String getMenuPage(Model model) {
        return "admin/add_menu";
    }

    @PostMapping("/add_menu")
    public String submitNewMenu(@ModelAttribute MenuModel menuModel, Model model) {
        Menu menu = new Menu(menuModel.getMenuName());
        Menu savedMenu = menuRepository.save(menu);
        model.addAttribute("menuId", savedMenu.getId());
        return "admin/add_menu_result";
    }
}
