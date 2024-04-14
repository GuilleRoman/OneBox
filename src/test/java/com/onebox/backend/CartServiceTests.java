package com.onebox.backend;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.onebox.backend.model.Cart;
import com.onebox.backend.model.Product;
import com.onebox.backend.service.CartService;

@RunWith(SpringRunner.class) // Replace with SpringRunner.Extension if using JUnit 5
@SpringBootTest
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    @Test
    public void testCreateCart() {
        Cart createdCart = cartService.createCart();

        assertNotNull(createdCart);
        assertTrue(createdCart.getId() > 0); // Ensure counter increments
    }

    @Test
    public void testCreateCart_SpecificId() {
        int desiredId = 10;
        Cart createdCart = cartService.createCart(desiredId);

        assertNotNull(createdCart);
        assertEquals(desiredId, createdCart.getId());
    }

    @Test
    public void testGetCart_ExistingCart() throws Exception {
        Cart createdCart = cartService.createCart();
        int createdCartId = createdCart.getId();

        Cart retrievedCart = cartService.getCart(createdCartId);

        assertNotNull(retrievedCart);
        assertEquals(createdCartId, retrievedCart.getId());
    }

    @Test
    public void testGetCart_NonExistingCart() throws Exception {
        int nonExistingId = 100;

        Cart retrievedCart = cartService.getCart(nonExistingId);

        assertNull(retrievedCart);
    }

    @Test
    public void testAddProduct_ExistingProduct() throws Exception {
        Cart createdCart = cartService.createCart();
        Product testProduct = new Product(1, 2);
        int initialQuantity = 2;
        createdCart.getProducts().add(testProduct);
        cartService.carts.put(createdCart.getId(), createdCart); // Simulate cache update

        int addedQuantity = 3;
        Object result = cartService.addProduct(createdCart.getId(), testProduct, addedQuantity);

        assertTrue(result instanceof Product); // Ensure product is returned
        Product updatedProduct = (Product) result;
        assertEquals(initialQuantity + addedQuantity, updatedProduct.getAmount());
    }

    @Test
    public void testAddProduct_NewProduct() throws Exception {
        Cart createdCart = cartService.createCart();
        Product testProduct = new Product(2, 5);
        int quantity = 5;

        Product result = (Product) cartService.addProduct(createdCart.getId(), testProduct, quantity);

        assertTrue(result instanceof Product); // Ensure product is returned
        Product addedProduct = result;
        assertEquals(quantity, addedProduct.getAmount());
        assertEquals(result, addedProduct); // Verify product added to cart
    }

    @Test(expected = Exception.class) // Expect exception for non-existing cart
    public void testAddProduct_NonExistingCart() throws Exception {
        int nonExistingId = 100;
        Product testProduct = new Product(3, 1);
        int quantity = 1;

        cartService.addProduct(nonExistingId, testProduct, quantity);
    }

    @Test
    public void testDeleteCart_ExistingCart() throws Exception {
        Cart createdCart = cartService.createCart();
        int createdCartId = createdCart.getId();

        boolean deleted = cartService.deleteCart(createdCartId);

        assertTrue(deleted);
        assertNull(cartService.getCart(createdCartId)); // Ensure cart is removed from cache
    }

    @Test
    public void testDeleteCart_NonExistingCart() throws Exception {
        int nonExistingId = 100;

        boolean deleted = cartService.deleteCart(nonExistingId);

        assertFalse(deleted);
    }

    // Additional tests could cover cache expiration behavior
}
