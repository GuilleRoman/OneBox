package com.onebox.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description This class represents the controller of the application, which
 *              will handle all requests. Since
 *              it is a simple application, I decided to go with a single
 *              controller.
 */
@RestController
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

    /**
     * Retrieves a cart by its id.
     * 
     * @param id of the cart to be retrieved.
     * @return ResponseEntity<String> with the message after the operations are
     *         performed.
     * @throws Exception
     */
    @GetMapping("/cart/{id}")
    public ResponseEntity<Object> getCart(@Valid @PathVariable int id) throws Exception {
        Cart cart = cartService.getCart(id);
        if (cart == null) {
            return new ResponseEntity<>("The cart with id " + id + " does not exist.",
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(cart);
        }

    }

    /**
     * Handles the removal of the specific cart provided with the id, which is
     * mandatory.
     * 
     * @param id of the cart.
     * @return ResponseEntity<String> with the message after the operations are
     *         performed.
     * @throws Exception
     */
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable @Valid int id) throws Exception {
        boolean deleted = this.cartService.deleteCart(id);
        if (deleted) {
            return ResponseEntity.ok("The cart with ID: " + id + " has been deleted");
        } else {
            return new ResponseEntity<>("The cart with id " + id + " does not exist.",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles the addition of products to a cart. ProductId is mandatory, cartId
     * and amount are optional.
     * If the cartId or Amount are not sent, it automatically sets a value of 1 for
     * both of them.
     * 
     * @param request       data received to create the product.
     * @param bindingResult object used for intercepting the data to perform
     *                      validations.
     * @return object which could be an error message or the products of the cart
     * @throws Exception
     */
    @PostMapping("/products")
    public ResponseEntity<Object> addProductToCart(@Valid @RequestBody AddProductDTO request,
            BindingResult bindingResult) throws Exception {

        int productId = request.getProductId();
        int amount = request.getAmount();
        int cartId = request.getCartId();

        if (cartId == 0 || amount == 0) {
            return new ResponseEntity<>("Please send positive values for id or amount",
                    HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productService.createProduct(request);

        Cart cart = this.cartService.getCart(cartId);

        if (cart != null) {
            this.cartService.addProduct(cart.getId(), newProduct, amount);
            return ResponseEntity.ok(cart.getProducts());
        } else {
            Cart newCart = cartService.createCart(cartId);
            newCart.getProducts().add(new Product(productId, amount));
            cartService.carts.put(cartId, newCart);
            return ResponseEntity.ok(newCart.getProducts());
        }
    }

}
