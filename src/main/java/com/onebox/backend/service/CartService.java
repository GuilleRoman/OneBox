package com.onebox.backend.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.onebox.backend.interfaces.ICartService;
import com.onebox.backend.model.Cart;
import com.onebox.backend.model.Product;

import lombok.ToString;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Handles business logic regarding the shopping carts.
 */
@Service
@ToString
public class CartService implements ICartService {

    public final LoadingCache<Integer, Cart> carts;
    // Since a database engine is not used, the counter will represent the automatic
    // id entries of the carts.
    private static int cartIdCounter = 0;

    /**
     * Constructor method, creates a cache storage where the carts will be hold.
     */
    public CartService() {
        carts = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES) // Set expiration time of 10 minutes
                .build(
                        new CacheLoader<Integer, Cart>() {
                            @Override
                            public Cart load(Integer cartId) throws Exception {
                                return null;
                            }
                        });
    }

    /**
     * Creates a new cart and increases the counter.
     * 
     * @return the cart newly created
     */
    public Cart createCart() {
        cartIdCounter++;
        Cart cart = new Cart(cartIdCounter);
        carts.put(cartIdCounter, cart);
        return cart;
    }

    /**
     * Overloaded method for creating a cart with a chosen Id.
     * 
     * @param id of the cart to be created.
     * @return the newly created cart
     */
    public Cart createCart(int cartId) {
        Cart cart = new Cart(cartId);
        carts.put(cartId, cart);
        return cart;
    }

    /**
     * Retrieves a cart by its id.
     * 
     * @param id of the cart to be retrieved.
     * @return the cart or null if not found.
     * @throws Exception
     */
    public Cart getCart(int cartId) throws Exception {
        try {
            Cart cart = carts.get(cartId);
            return cart;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds a product to the cart. If the product already exists in the cart, the
     * amount is increased.
     * 
     * @param id of the cart to be retrieved.
     * @return the product after the changes or creation.
     * @throws Exception
     */
    public Object addProduct(int cartId, Product product, int quantity) throws Exception {
        try {
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
        } catch (Exception e) {

            return e;
        }
        return product;
    }

    /**
     * Delete a cart by its id.
     * 
     * @param id of the cart to be retrieved.
     * @return boolean representing the success or failure of the operation
     * @throws Exception
     */
    public boolean deleteCart(int cartId) throws Exception {
        Cart cart = getCart(cartId);
        if (cart != null) {
            carts.invalidate(cartId);
            return true;
        }
        return false;
    }
}