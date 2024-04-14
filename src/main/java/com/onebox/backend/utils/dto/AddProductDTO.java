package com.onebox.backend.utils.dto;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Data Transfer Object which will be validated before any
 *              operations.
 */
public class AddProductDTO {
    @NotNull(message = "There must be a valid productId")
    @Min(value = 1, message = "ProductId must be positive")
    private int productId;
    private Integer cartId;
    private Integer amount;

    /**
     * Constructor method. Creates a new AddProductDTO object. If cartId or amount
     * are not provided, the missing value will be set to 1.
     * 
     */
    public AddProductDTO(int productId, Optional<Integer> cartId, Optional<Integer> amount) {
        this.productId = productId;
        this.cartId = cartId.orElse(1);
        this.amount = amount.orElse(1);
    }

    /**
     * Getter method for productId attribute.
     * 
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Getter method for amount attribute.
     * 
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter method for cartId attribute.
     * 
     * @return the cartId
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Setter method for productId attribute.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Setter method for amount attribute.
     */
    public void setamount(int amount) {
        this.amount = amount;
    }

}