package com.example.ooad.model;

public class CheckoutModel {
    private String addressStreetName;
    private String addressUnit;
    private String addressCity;
    private String addressState;
    private String addressZipcode;

    private String paymentCardNumber;
    private String paymentExpiry;
    private String paymentBillingZipcode;
    private String paymentCvv;

    public CheckoutModel(String addressStreetName, String addressUnit, String addressCity, String addressState, String addressZipcode, String paymentCardNumber, String paymentExpiry, String paymentBillingZipcode, String paymentCvv) {
        this.addressStreetName = addressStreetName;
        this.addressUnit = addressUnit;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipcode = addressZipcode;
        this.paymentCardNumber = paymentCardNumber;
        this.paymentExpiry = paymentExpiry;
        this.paymentBillingZipcode = paymentBillingZipcode;
        this.paymentCvv = paymentCvv;
    }

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public String getAddressUnit() {
        return addressUnit;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public String getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public String getPaymentExpiry() {
        return paymentExpiry;
    }

    public String getPaymentBillingZipcode() {
        return paymentBillingZipcode;
    }

    public String getPaymentCvv() {
        return paymentCvv;
    }
}
