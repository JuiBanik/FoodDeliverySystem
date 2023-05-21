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
    private String orderDetail;
    private String orderCurrentStatus;

    public User(String userName, String password) {
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
