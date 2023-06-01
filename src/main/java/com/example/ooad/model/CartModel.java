package com.example.ooad.model;

public class CartModel {
    private String itemName;
    private String menuName;

    public CartModel(String itemName, String menuName) {
        this.itemName = itemName;
        this.menuName = menuName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
