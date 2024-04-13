package com.onebox.backend.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.ToString;

@ToString
public class Cart {
    private int id;
    private List<Product> products = new ArrayList<>();
    private String createdTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new Date(System.currentTimeMillis()));

    public Cart(int cartIdCounter) {
        this.id = cartIdCounter;
    }

    // Methods to add/remove products, check cart expiration
    public List<Product> getProducts() {
        return this.products;
    }

    public void addProducts(Product product) {
        this.products.add(product);

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    // Setters (optional, might not be needed for in-memory storage)
    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}