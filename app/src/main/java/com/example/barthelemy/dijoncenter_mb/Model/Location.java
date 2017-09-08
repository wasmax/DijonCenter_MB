package com.example.barthelemy.dijoncenter_mb.Model;

import java.io.Serializable;

/**
 * Created by Max on 08/09/2017.
 */
public class Location implements Serializable {

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String address;
    String postalCode;
    String city;

    public Location(String address, String postalCode, String city) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }
}
