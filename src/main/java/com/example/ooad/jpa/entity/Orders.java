package com.example.ooad.jpa.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderId;

    private Long userId; //create a foreign key

    private Boolean ordered;

    public Boolean getOrdered() {
        return ordered;
    }

    public Boolean getOrderReceived() {
        return orderReceived;
    }

    public Boolean getInTransit() {
        return inTransit;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    private Boolean orderReceived;
    private Boolean inTransit;
    private Boolean delivered;

   // @OneToOne
    //private User;


    public Orders(Long userId, Boolean ordered,Boolean orderReceived, Boolean inTransit, Boolean delivered) {
        this.ordered = ordered; //initially set this as true and rest as false
        this.orderReceived = orderReceived;
        this.inTransit = inTransit;
        this.delivered = delivered;
    }

    public Long getId() {
        return orderId;
    }



}
