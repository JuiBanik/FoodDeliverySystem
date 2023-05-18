package com.example.ooad.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String menuName;

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    public Long getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

}
