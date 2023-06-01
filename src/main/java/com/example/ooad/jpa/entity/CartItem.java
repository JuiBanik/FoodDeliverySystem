package com.example.ooad.jpa.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "cartItems")
public class CartItem {
    public static final String PENDING_CART_STATUS = "pending";
    public static final String CLOSED_CART_STATUS = "closed";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long cartId;
    private Long quantity;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @OneToOne(targetEntity = Item.class, fetch = FetchType.EAGER)
    private Item item;

    private String cartStatus;

    public CartItem() {

    }

    public CartItem(Long quantity, User user, Item item, String cartStatus) {
        this.quantity = quantity;
        this.user = user;
        this.item = item;
        this.cartStatus = cartStatus;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
