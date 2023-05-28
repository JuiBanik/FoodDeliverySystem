package com.example.ooad.jpa.entity;

import jakarta.persistence.*;

/**
 * This Java class is responsible for interacting with the table in database and it also creates the table if it
 * does not exist using the class definition. Table name is defined by @Table (name = XXXX) annotation.
 */
@Entity
@Table(name = "adminuser")
public class AdminUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String userName;

    public AdminUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminUser() {

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private String password;
}
