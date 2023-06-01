package com.example.ooad.controller.admin;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.OrdersRepository;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.CartItem;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.jpa.entity.Orders;
import com.example.ooad.model.OrderModel;
import com.example.ooad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewOrdersController {
    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping("/admin_view_orders")
    public String getAdminViewOrdersPage(Model model) {
        Iterable<Orders> ordersList = ordersRepository.findAll();
        model.addAttribute("ordersList", ordersList);
        return "admin/admin_view_orders"; //name of view/html page to be loaded
        //ArrayList<String> orderStatusOptions = new ArrayList<String>("Placed", "Received", "Confirmed by Restaurant", "Picked up", "In Transit", "Complete");

        //model.addAttribute("orderStatusOptions", Arrays.asList("Placed", "Received", "Confirmed by Restaurant", "Picked up", "In Transit","Complete"));
    }

    @PostMapping("/admin_update_order_status")
    public String postAdminUpdateOrderStatus(Model model,
                                             @ModelAttribute OrderModel orderModel) {
        System.out.println(orderModel.getOrderId());
        System.out.println(orderModel.getOrderStatus());
        Optional<Orders> ordersOptional = ordersRepository.findById(orderModel.getOrderId());
        if (!ordersOptional.isPresent()) {
            return "redirect:admin_view_orders";
        }
        Orders order = ordersOptional.get();
        order.setOrderStatus(orderModel.getOrderStatus());
        ordersRepository.save(order);
        return "redirect:admin_view_orders";
    }


}
