package com.example.ooad.controller.admin;

import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class ViewMenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/admin_view_menu")
    public String getAdminViewMenuPage(Model model, HttpSession httpSession) {
       // model.addAttribute("username", userName);
        System.out.println("Fetching menu from database");
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        Iterable<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList", menuList);

        return "admin/admin_view_menu"; //name of view/html page to be loaded
    }

    @PostMapping("/admin_view_menu")
    public String manageMenu(@ModelAttribute UserModel userModel,
                                   Model model,
                                   HttpSession httpSession) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/admin_manage_menu";
    }

}