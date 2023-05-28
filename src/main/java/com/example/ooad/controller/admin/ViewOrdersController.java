package com.example.ooad.controller.admin;
import com.example.ooad.jpa.MenuRepository;
import com.example.ooad.jpa.OrdersRepository;
import com.example.ooad.jpa.entity.Menu;
import com.example.ooad.jpa.entity.Orders;
import com.example.ooad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class ViewOrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/admin_view_orders")
    public String getAdminViewOrdersPage(@RequestParam("username") String userName,
                                       Model model) {
        // model.addAttribute("username", userName);
        Iterable<Orders> ordersList = ordersRepository.findAll();
        model.addAttribute("ordersList", ordersList);
        return "admin/admin_view_orders"; //name of view/html page to be loaded
    }

    @PostMapping("/admin_view_orders")
    public String manageOrders(@ModelAttribute UserModel userModel,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        // redirectAttributes.addAttribute("username", adminUser.getUserName());
        return "admin/admin_manage_orders";
    }
}
