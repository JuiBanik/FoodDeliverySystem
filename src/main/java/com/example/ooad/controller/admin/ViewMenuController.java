package com.example.ooad.controller.admin;
import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ViewMenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/admin_view_menu")
    public String getAdminViewMenuPage(Model model) {
       // model.addAttribute("username", userName);
        System.out.println("Fetching menu from database");
        Iterable<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList", menuList);

        return "admin/admin_view_menu"; //name of view/html page to be loaded
    }

    @PostMapping("/admin_view_menu")
    public String manageMenu(@ModelAttribute UserModel userModel,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        // redirectAttributes.addAttribute("username", adminUser.getUserName());
        return "admin/admin_manage_menu";
    }

}