package com.example.ooad.controller.admin;


import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.model.ItemModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class AddItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/add_item")
    public String getItemPage(Model model, HttpSession httpSession) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/add_item";
    }

    @PostMapping("/add_item")
    public String submitNewItem(@ModelAttribute ItemModel itemModel, Model model,
                                HttpSession httpSession) {
        Item newItem = new Item(itemModel.getItemName(),itemModel.getItemPrice());
        Menu menu = menuRepository.findByMenuName(itemModel.getMenuCategory());
        newItem.setMenu(menu);
        Item savedItem = itemRepository.save(newItem);
        if (menu.getItemList() == null || menu.getItemList().size() == 0) {
            List<Item> itemList = new ArrayList<>();
            itemList.add(newItem);
            menu.setItemList(itemList);
        } else {
            menu.getItemList().add(savedItem);
        }
        menuRepository.save(menu);
        model.addAttribute("itemId", savedItem.getId());
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        return "admin/add_item_result";
    }
}
