package com.example.ooad.jpa.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long itemId;

    private String itemName;

    @OneToOne(targetEntity = Menu.class, fetch = FetchType.EAGER)
    private Menu menu;

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    private Double itemPrice;

    public Item(String itemName, Double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Long getId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Item() {

    }

}
