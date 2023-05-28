package com.example.ooad.model;

public class ItemModel {
    private String itemName;
    private Double itemPrice;
    private String menuCategory;

    public ItemModel(String itemName, Double itemPrice, String menuCategory) {

        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.menuCategory = menuCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getMenuCategory() {
        return menuCategory;
    }
}
