package com.example.ooad.controller.user;

import com.example.ooad.jpa.CartItemsRepository;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.CartItem;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.jpa.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.ooad.jpa.entity.CartItem.PENDING_CART_STATUS;

@Controller
public class UserHomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private UserLoginUtil userLoginUtil;

    @GetMapping("/user_home")
    public String getUserHomePage(Model model,
                                  HttpSession session,
                                  @RequestParam(required=false) String message) {
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user == null) {
            return "redirect:user_login";
        }

        System.out.println("Fetching menu from database");
        Iterable<Menu> menuList = menuRepository.findAll();
        List<Menu> menuListSored = new ArrayList<>();
        menuList.forEach(menu -> menuListSored.add(menu));
        menuListSored.sort(Comparator.comparing(Menu::getMenuName));

        model.addAttribute("menuList", menuList);
        model.addAttribute("message", message);

        Iterable<CartItem> cartItems = cartItemsRepository.findByUserAndCartStatus(user, PENDING_CART_STATUS);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItems.forEach( cartItem -> {
            cartItemList.add(cartItem);
        });
        long totalQuantity = cartItemList.stream().mapToLong(item -> item.getQuantity()).sum();
        double totalPrice = cartItemList.stream()
                .mapToDouble(item -> item.getQuantity()* item.getItem().getItemPrice()).sum();
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrice", totalPrice);

        return "user_home";
    }
}
