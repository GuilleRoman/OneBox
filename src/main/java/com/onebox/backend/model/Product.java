package com.onebox.backend.model;

import com.onebox.backend.utils.enums.ProductEnum;

import lombok.ToString;

@ToString
public class Product {

    private int id;
    private String description;
    private int amount;

    // Constructor (optional)
    public Product(int id, int amount) {
        this.id = id;
        this.description = ProductEnum.getById(id).getDescription();
        this.amount = amount;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
