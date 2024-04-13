package com.onebox.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.backend.model.Cart;
import com.onebox.backend.model.Product;
import com.onebox.backend.service.CartService;
import com.onebox.backend.service.ProductService;
import com.onebox.backend.utils.dto.AddProductDTO;

import jakarta.validation.Valid;

@RestController
@ControllerAdvice
@RequestMapping("/")
public class MainController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@Valid @PathVariable int id) {
        Cart cart = cartService.getCart(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable @Valid int id) {
        boolean deleted = this.cartService.deleteCart(id);
        if (deleted) {
            return ResponseEntity.ok("The cart with ID: " + id + " has been deleted");
        } else {
            return ResponseEntity.ok("The cart with ID: " + id + " does not exist");
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Object> addProductToCart(@Valid @RequestBody AddProductDTO request,
            BindingResult bindingResult) throws Exception {

        if (request.getCartId() == 0 || request.getQuantity() == 0) {
            return new ResponseEntity<>("Please send positive values for id or quantity", HttpStatus.BAD_REQUEST);
        }
        Integer cartId = request.getCartId();

        Product newProduct = productService.createProduct(request);

        Cart cart = this.cartService.getCart(cartId);

        if (cart != null) {
            this.cartService.addProduct(cart.getId(), newProduct, request.getQuantity());
        } else {
            Cart newCart = cartService.createCart();
            newCart.addProducts(newProduct);
        }
        return ResponseEntity.ok(cartService.carts);
    }

}
