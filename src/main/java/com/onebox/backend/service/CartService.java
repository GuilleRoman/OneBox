package com.onebox.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.onebox.backend.model.Cart;
import com.onebox.backend.model.Product;

import lombok.ToString;

@Service
@ToString
public class CartService {

    public final Map<Integer, Cart> carts = new HashMap<>(); // In-memory cart storage
    private static int cartIdCounter = 0;

    public Cart createCart() {
        cartIdCounter++;
        Cart cart = new Cart(cartIdCounter);
        carts.put(cartIdCounter, cart);
        return cart;
    }

    public Cart getCart(int cartId) {
        return carts.get(cartId);
    }

    public Product addProduct(int cartId, Product product, int quantity) {
        Cart cart = getCart(cartId);
        if (cart != null) {
            for (Product currentProduct : cart.getProducts()) {
                if (currentProduct.getId() == product.getId()) {
                    currentProduct.setAmount(quantity + currentProduct.getAmount());
                } else {
                    cart.addProducts(product);
                }
            }

            return product;
        }
        return null;
    }

    public boolean deleteCart(int cartId) {
        Cart cart = getCart(cartId);
        if (cart != null) {
            carts.remove(cartId);
            return true;
        }
        return false;
    }
}