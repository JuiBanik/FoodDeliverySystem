package com.example.ooad.controller.admin;

import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.model.ItemModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;


@Controller
public class EditItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/edit_item")
    public String getItemPage(Model model, HttpSession httpSession) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/edit_item";
    }

    @PostMapping("/edit_item")
    public String editItemPrice(@ModelAttribute ItemModel itemModel, Model model, HttpSession httpSession) {

        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        Item itemToEdit = itemRepository.findByItemName(itemModel.getItemName());
        if(itemToEdit == null){
            System.out.println("Item not found!");
            return "admin/edit_item";
        }
        else {
         //item found successfully to be edited
            itemToEdit.setItemPrice(itemModel.getItemPrice());
            itemRepository.save(itemToEdit);
            System.out.println("item price edited!");

        }
        model.addAttribute("message", "Item's Price edited successfully!");
        return "admin/edit_item";
    }
}
