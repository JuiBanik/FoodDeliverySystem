package com.example.ooad.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String menuName;

    @OneToMany( targetEntity=Item.class, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Item> itemList;

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    public Long getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    public Menu() {

    }

}
