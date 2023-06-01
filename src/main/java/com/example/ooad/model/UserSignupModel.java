package com.example.ooad.model;

/**
 * This class is used to store information entered by the user on webpage and pass it to Controller.
 */
public class UserSignupModel {
    private String userName;
    private String password;
    private String email;



    private String phoneNumber;

    public UserSignupModel(String userName, String password, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

