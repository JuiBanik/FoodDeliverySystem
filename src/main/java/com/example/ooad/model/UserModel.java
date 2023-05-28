package com.example.ooad.model;

/**
 * This class is used to store information entered by the user on webpage and pass it to Controller.
 */
public class UserModel {
    private String userName;
    private String password;

    public UserModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
