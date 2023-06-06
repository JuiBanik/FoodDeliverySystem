package com.example.ooad.controller.admin;

import com.example.ooad.jpa.OrdersRepository;
import com.example.ooad.jpa.entity.Orders;
import com.example.ooad.model.OrderModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class ViewOrdersController {
    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping("/admin_view_orders")
    public String getAdminViewOrdersPage(Model model, HttpSession httpSession) {
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
        Iterable<Orders> ordersList = ordersRepository.findAll();
        model.addAttribute("ordersList", ordersList);
        return "admin/admin_view_orders"; //name of view/html page to be loaded
        //ArrayList<String> orderStatusOptions = new ArrayList<String>("Placed", "Received", "Confirmed by Restaurant", "Picked up", "In Transit", "Complete");

        //model.addAttribute("orderStatusOptions", Arrays.asList("Placed", "Received", "Confirmed by Restaurant", "Picked up", "In Transit","Complete"));
    }

    @PostMapping("/admin_update_order_status")
    public String postAdminUpdateOrderStatus(Model model,
                                             @ModelAttribute OrderModel orderModel,
                                             HttpSession httpSession) {
        System.out.println(orderModel.getOrderId());
        System.out.println(orderModel.getOrderStatus());
        model.addAttribute(USERNAME_ATTRIBUTE_NAME, httpSession.getAttribute(USERNAME_ATTRIBUTE_NAME));
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
