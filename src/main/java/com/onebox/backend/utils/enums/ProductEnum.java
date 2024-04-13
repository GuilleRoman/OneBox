package com.onebox.backend.utils.enums;

public enum ProductEnum {
  ENTRY_TICKET(1, "Entry Ticket"),
  CANCELLATION_INSURANCE(2, "Cancellation Insurance"),
  ;

  private final int id;
  private final String description;

  ProductEnum(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  // You can add a method to get the Product enum based on its ID
  public static ProductEnum getById(int id) {
    for (ProductEnum product : values()) {
      if (product.getId() == id) {
        return product;
      }
    }
    throw new IllegalArgumentException("Invalid product ID: " + id);
  }
}