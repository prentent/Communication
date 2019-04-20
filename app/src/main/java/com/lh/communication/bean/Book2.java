package com.lh.communication.bean;

import java.io.Serializable;

public class Book2 implements Serializable {
    private String name;
    private float rice;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRice() {
        return rice;
    }

    public void setRice(float rice) {
        this.rice = rice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
