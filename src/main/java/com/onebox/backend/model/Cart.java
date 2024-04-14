package com.onebox.backend.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.ToString;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Shopping Cart model.
 */
@ToString
public class Cart {
    private int id;
    private List<Product> products = new ArrayList<>();
    private String createdTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new Date(System.currentTimeMillis()));

    /**
     * Creates a new cart with the id provided.
     * 
     * @param cartIdCounter the current counter integer for the new cart.
     */
    public Cart(int cartIdCounter) {
        this.id = cartIdCounter;
    }

    // Getter methods

    public List<Product> getProducts() {
        return this.products;
    }

    public int getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Adds a product to the cart
     * 
     * @param product the product that will be added to the cart.
     */
    public void addProducts(Product product) {
        this.products.add(product);

    }
}