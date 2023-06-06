package com.example.ooad.controller.admin;

import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.model.MenuModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class AddMenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/add_menu")
    public String getMenuPage(Model model, HttpSession httpSession) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/add_menu";
    }

    @PostMapping("/add_menu")
    public String submitNewMenu(@ModelAttribute MenuModel menuModel, Model model, HttpSession httpSession) {
        Menu menu = new Menu(menuModel.getMenuName());
        Menu savedMenu = menuRepository.save(menu);
        model.addAttribute("menuId", savedMenu.getId());
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/add_menu_result";
    }
}
