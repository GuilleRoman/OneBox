package com.onebox.backend.utils.dto;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddProductDTO {
    @NotNull(message = "There must be a valid productId")
    @Min(value = 1, message = "ProductId must be positive")
    private int productId;
    private Integer cartId;
    private Integer quantity;

    public AddProductDTO(int productId, Optional<Integer> cartId, Optional<Integer> quantity) {
        this.productId = productId;
        this.cartId = cartId.orElse(1);
        this.quantity = quantity.orElse(1);
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}