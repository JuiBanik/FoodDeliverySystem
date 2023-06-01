package com.example.ooad.controller.user;

import com.example.ooad.jpa.CartItemsRepository;
import com.example.ooad.jpa.ItemRepository;
import com.example.ooad.jpa.OrdersRepository;
import com.example.ooad.jpa.UserRepository;
import com.example.ooad.jpa.entity.CartItem;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Orders;
import com.example.ooad.jpa.entity.User;
import com.example.ooad.model.CartModel;
import com.example.ooad.model.CheckoutModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ooad.jpa.entity.CartItem.CLOSED_CART_STATUS;
import static com.example.ooad.jpa.entity.CartItem.PENDING_CART_STATUS;

@Controller
public class CartController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserLoginUtil userLoginUtil;

    @PostMapping("/add_to_cart")
    public String addToCart(@ModelAttribute CartModel cartModel,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user == null) {
            return "redirect:user_login";
        }

        redirectAttributes.addAttribute("message",
                String.format("%s added to cart successfully", cartModel.getItemName()));
        Item item = itemRepository.findByItemName(cartModel.getItemName());

        CartItem cartItem = cartItemsRepository.findByUserAndItemAndCartStatus(user, item, PENDING_CART_STATUS);
        if (cartItem == null) {
            CartItem newCartItem = new CartItem(1l, user, item,PENDING_CART_STATUS);
            cartItemsRepository.save(newCartItem);
            System.out.println("Created new item in cart");
        } else {
            cartItem.setQuantity(cartItem.getQuantity()+1l);
            cartItemsRepository.save(cartItem);
            System.out.println("Updated quantity for existing item in cart");
        }
        redirectAttributes.addAttribute("message", "Item added to cart");
        return "redirect:user_home";
    }

    @GetMapping("/checkout_cart")
    public String getCheckoutCart(Model model, HttpSession session) {
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user == null) {
            return "redirect:user_login";
        }
        return "user_checkout";
    }

    @PostMapping("/user_checkout")
    public String postCheckoutCart(@ModelAttribute CheckoutModel checkoutModel,
                                   Model model,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        // address information
        // payment information
        //store in order table
        User user = userLoginUtil.getLoggedInUser(model, session);
        if (user == null) {
            return "redirect:user_login";
        }

        Iterable<CartItem> cartItems = cartItemsRepository.findByUserAndCartStatus(user, PENDING_CART_STATUS);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItems.forEach(cartItemList::add);
        double totalOrderPrice = cartItemList.stream()
                .mapToDouble(cartItem -> cartItem.getItem().getItemPrice() * cartItem.getQuantity())
                .sum();
        Orders newOrder = new Orders(user,
                checkoutModel.getAddressStreetName(),
                checkoutModel.getAddressUnit(),
                checkoutModel.getAddressCity(),
                checkoutModel.getAddressState(),
                checkoutModel.getAddressZipcode(),
                checkoutModel.getPaymentCardNumber(),
                checkoutModel.getPaymentExpiry(),
                checkoutModel.getPaymentBillingZipcode(),
                checkoutModel.getPaymentCvv(),
                totalOrderPrice,
                "Placed",
                cartItemList
        );
        ordersRepository.save(newOrder);
        cartItemList.forEach(cartItem -> {
            cartItem.setCartStatus(CLOSED_CART_STATUS);
            cartItemsRepository.save(cartItem);
        });
        redirectAttributes.addAttribute("message", "Order Placed Successfully");
        return "redirect:user_view_order";
    }
}
