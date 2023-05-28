package com.example.ooad.controller.admin;

import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeleteMenuController {
    @Autowired
    private MenuRepository menuRepository; // creating object of the required repository

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/delete_menu")
    public String getDeleteMenuPage(Model model) {

        return "admin/delete_menu"; //name of view/html page to be loaded
    }

    @PostMapping("/delete_menu")
    public String submitDeleteMenu(@ModelAttribute MenuModel menuModel,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        System.out.println(menuModel.getMenuName());


        Menu menuToBeDeleted = menuRepository.findByMenuName(menuModel.getMenuName());
        if (menuToBeDeleted == null ) {
            System.out.println("Menu not found!");
        }
        else {
            List<Item> itemList = menuToBeDeleted.getItemList();
            //remove associations
            menuToBeDeleted.setItemList(null);
            menuRepository.save(menuToBeDeleted);
            //remove items
            for (Item item: itemList) {
                item.setMenu(null);
                itemRepository.save(item);
                itemRepository.delete(item);
            }
            //remove menu
            menuRepository.delete(menuToBeDeleted);
            System.out.println("Menu deleted!");
        }
        model.addAttribute("message", "Menu deleted successfully!");
        return "admin/delete_menu";
    }

}
