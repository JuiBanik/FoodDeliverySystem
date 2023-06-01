package com.example.ooad.jpa.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "userInfo")
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public User(String userName, String password, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public User()
    {

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
}
