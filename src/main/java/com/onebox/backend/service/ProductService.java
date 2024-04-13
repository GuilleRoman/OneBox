package com.onebox.backend.service;

import org.springframework.stereotype.Service;

import com.onebox.backend.model.Product;
import com.onebox.backend.utils.dto.AddProductDTO;

import lombok.Data;

@Service
@Data
public class ProductService {

    public Product createProduct(AddProductDTO dto) {
        return new Product(dto.getProductId(), dto.getQuantity());

    }

}
