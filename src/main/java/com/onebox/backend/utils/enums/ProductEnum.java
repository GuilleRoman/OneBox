package com.onebox.backend.utils.enums;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Static Enumerator for the different product types based on their
 *              id.
 */
public enum ProductEnum {
  ENTRY_TICKET(1, "Entry Ticket"),
  CANCELLATION_INSURANCE(2, "Cancellation Insurance"),
  ;

  private final int id;
  private final String description;

  // constructor
  ProductEnum(int id, String description) {
    this.id = id;
    this.description = description;
  }

  // Getters

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Gets the correct Product Enum object for the id provided in order to access
   * the description.
   * 
   * @param id of the cart.
   * @return the productEnum selected.
   */
  public static ProductEnum getById(int id) {
    for (ProductEnum product : values()) {
      if (product.getId() == id) {
        return product;
      }
    }
    throw new IllegalArgumentException("Invalid product ID: " + id);
  }
}