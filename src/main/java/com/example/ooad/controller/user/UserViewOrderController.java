package com.example.ooad.controller.user;

import com.example.ooad.jpa.OrdersRepository;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class UserViewOrderController {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginUtil userLoginUtil;

    @GetMapping("/user_view_order")
    public String getUserViewPage(Model model,
                                  HttpSession session) {
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user == null) {
            return "redirect:user_login";
        }

        Iterable<Orders> ordersList = ordersRepository.findByUser(user);

        List<Orders> activeOrders = new ArrayList<>();
        List<Orders>  completedOrders = new ArrayList<>();

        ordersList.forEach( orders -> {
            if(orders.getOrderStatus().equalsIgnoreCase("complete")){
                completedOrders.add(orders);
            }
            else{
                activeOrders.add(orders);
            }
        });
        activeOrders.sort(Comparator.comparing(Orders::getOrderDate).reversed());
        completedOrders.sort(Comparator.comparing(Orders::getOrderDate).reversed());

        model.addAttribute("activeOrders", activeOrders);

        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("orderStatus", "In Transit");

        return "user_view_order";
    }

}
