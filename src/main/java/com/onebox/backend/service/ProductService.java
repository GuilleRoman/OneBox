package com.onebox.backend.service;

import org.springframework.stereotype.Service;

import com.onebox.backend.model.Product;
import com.onebox.backend.utils.dto.AddProductDTO;

import lombok.Data;

/**
 * @author Guillermo Román
 * @version 1.0
 * @Description Handles business logic regarding the products contained in the
 *              shopping carts.
 */
@Service
@Data
public class ProductService {

    /**
     * Creates a new product.
     * 
     * @param dto Data Transfer Object received in the request with the information
     *            to create the product.
     * @return the newly created product.
     */
    public Product createProduct(AddProductDTO dto) {
        return new Product(dto.getProductId(), dto.getAmount());

    }

}
