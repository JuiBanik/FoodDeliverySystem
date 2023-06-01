package com.example.ooad.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderId;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    private String addressStreetName;
    private String addressUnit;
    private String addressCity;
    private String addressState;
    private String addressZipcode;

    private String paymentCardNumber;
    private String paymentExpiry;
    private String paymentBillingZipcode;
    private String paymentCvv;

    private Double totalPrice;

    private String orderStatus;

    @OneToMany( targetEntity=CartItem.class, fetch = FetchType.EAGER)
    private List<CartItem> checkoutItemList; //items and their quantity to be stored here for the order placed

    private LocalDate orderDate;

    public Orders(User user, String addressStreetName, String addressUnit, String addressCity, String addressState, String addressZipcode, String paymentCardNumber, String paymentExpiry, String paymentBillingZipcode, String paymentCvv, Double totalPrice, String orderStatus, List<CartItem> checkoutItemList) {
        this.user = user;
        this.addressStreetName = addressStreetName;
        this.addressUnit = addressUnit;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipcode = addressZipcode;
        this.paymentCardNumber = paymentCardNumber;
        this.paymentExpiry = paymentExpiry;
        this.paymentBillingZipcode = paymentBillingZipcode;
        this.paymentCvv = paymentCvv;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.checkoutItemList = checkoutItemList;
        this.orderDate = LocalDate.now();
    }

    public Orders(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public void setAddressStreetName(String addressStreetName) {
        this.addressStreetName = addressStreetName;
    }

    public String getAddressUnit() {
        return addressUnit;
    }

    public void setAddressUnit(String addressUnit) {
        this.addressUnit = addressUnit;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    public String getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public void setPaymentCardNumber(String paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public String getPaymentExpiry() {
        return paymentExpiry;
    }

    public void setPaymentExpiry(String paymentExpiry) {
        this.paymentExpiry = paymentExpiry;
    }

    public String getPaymentBillingZipcode() {
        return paymentBillingZipcode;
    }

    public void setPaymentBillingZipcode(String paymentBillingZipcode) {
        this.paymentBillingZipcode = paymentBillingZipcode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getPaymentCvv() {
        return paymentCvv;
    }

    public void setPaymentCvv(String paymentCvv) {
        this.paymentCvv = paymentCvv;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<CartItem> getCheckoutItemList() {
        return checkoutItemList;
    }

    public void setCheckoutItemList(List<CartItem> checkoutItemList) {
        this.checkoutItemList = checkoutItemList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
