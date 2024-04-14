package com.onebox.backend.interfaces;

import com.onebox.backend.model.Cart;
import com.onebox.backend.model.Product;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Interface that will create the contracts on the CartService
 *              class to be fulfilled.
 */
public interface ICartService {
    Cart createCart();

    Cart getCart(int cartId) throws Exception;

    boolean deleteCart(int cartId) throws Exception;

    Object addProduct(int cartId, Product product, int quantity) throws Exception;
}
