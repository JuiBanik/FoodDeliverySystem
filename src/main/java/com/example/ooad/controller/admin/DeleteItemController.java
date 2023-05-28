package com.example.ooad.controller.admin;

import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.model.ItemModel;
import com.example.ooad.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DeleteItemController {
    @Autowired
    private ItemRepository itemRepository; // creating object of the required repository

    @Autowired
    private MenuRepository menuRepository; // creating object of the required repository

    @GetMapping("/delete_item")
    public String getDeleteItemPage(Model model) {

        return "admin/delete_item"; //name of view/html page to be loaded
    }

    @PostMapping("/delete_item")
    public String submitDeleteMenu(@ModelAttribute ItemModel itemModel,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        System.out.println(itemModel.getItemName());


        Item itemToBeDeleted = itemRepository.findByItemName(itemModel.getItemName());
        if (itemToBeDeleted == null ) {
            System.out.println("Item not found!");
        }
        else{
            //menu found successfully
            Menu menu = itemToBeDeleted.getMenu();
            List<Item> itemInList = menu.getItemList();
            itemInList.remove(itemToBeDeleted);
            menu.setItemList(itemInList);
            menuRepository.save(menu);

            itemRepository.delete(itemToBeDeleted);
            System.out.println("item deleted!");
        }
        model.addAttribute("message", "Item Deleted successfully!");
        return "admin/delete_item";
    }
}

