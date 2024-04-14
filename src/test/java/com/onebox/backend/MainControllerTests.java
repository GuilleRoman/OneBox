package com.onebox.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.onebox.backend.controller.MainController;
import com.onebox.backend.model.Cart;
import com.onebox.backend.service.CartService;
import com.onebox.backend.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {

    @Autowired
    private MainController controller;

    @MockBean
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @Test
    public void testCreateCart() {
        Cart mockCart = new Cart(1); // Create a mock cart object
        when(cartService.createCart()).thenReturn(mockCart);

        ResponseEntity<Cart> response = controller.createCart();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCart, response.getBody());
    }

    @Test
    public void testGetCart_ExistingCart() throws Exception {
        int cartId = 1;
        Cart mockCart = new Cart(cartId);
        when(cartService.getCart(cartId)).thenReturn(mockCart);

        ResponseEntity<Object> response = controller.getCart(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCart, response.getBody());
    }

    @Test
    public void testGetCart_NonExistingCart() throws Exception {
        int cartId = 1;
        when(cartService.getCart(cartId)).thenReturn(null);

        ResponseEntity<Object> response = controller.getCart(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        String expectedMessage = "The cart with id " + cartId + " does not exist.";
        assertTrue(response.getBody().toString().contains(expectedMessage));
    }

    @Test
    public void testDeleteCart_ExistingCart() throws Exception {
        int cartId = 1;
        when(cartService.deleteCart(cartId)).thenReturn(true);

        ResponseEntity<String> response = controller.deleteCart(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        String expectedMessage = "The cart with ID: " + cartId + " has been deleted";
        assertEquals(expectedMessage, response.getBody());
    }

    @Test
    public void testDeleteCart_NonExistingCart() throws Exception {
        int cartId = 1;
        when(cartService.deleteCart(cartId)).thenReturn(false);

        ResponseEntity<String> response = controller.deleteCart(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        String expectedMessage = "The cart with id " + cartId + " does not exist.";
        assertTrue(response.getBody().toString().contains(expectedMessage));
    }

}
