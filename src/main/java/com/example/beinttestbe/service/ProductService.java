package com.example.beinttestbe.service;


import com.example.beinttestbe.dto.ProductDto;
import com.example.beinttestbe.entity.Product;
import com.example.beinttestbe.response.ProductResponse;
import com.example.beinttestbe.utils.PaginatedResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product save(ProductDto productDto);

    Product findById(UUID id);

    PaginatedResponse<ProductResponse> findAll(int page, int size);

    List<Product> findByCategory(String category);

    Product update(UUID id, ProductDto updatedEntity);

    void delete(UUID id);
}
