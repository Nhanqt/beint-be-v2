package com.example.beinttestbe.service.impl;


import com.example.beinttestbe.dto.ProductDto;
import com.example.beinttestbe.entity.Product;
import com.example.beinttestbe.exception.ProductExistException;
import com.example.beinttestbe.exception.ProductNotFoundException;
import com.example.beinttestbe.repository.ProductRepository;
import com.example.beinttestbe.response.ProductResponse;
import com.example.beinttestbe.service.ProductService;
import com.example.beinttestbe.utils.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(ProductDto productDto) {

        Product productExistCode = productRepository.findByCode(productDto.getCode().toUpperCase());

        if (productExistCode != null) {
            throw new ProductExistException("Product with code " + productDto.getCode() + " already exists.");
        }

        Product product = new Product();
        product.setCode(productDto.getCode().toUpperCase());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setBranch(productDto.getBranch());
        product.setType(productDto.getType());
        product.setDescription(productDto.getDescription());

        // Generate UUID for the product
        UUID uuid = UUID.randomUUID();
        product.setId(uuid);

        return productRepository.save(product);
    }

    @Override
    public Product findById(UUID id) {
        return null;
    }

    @Override
    public PaginatedResponse<ProductResponse> findAll(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return new PaginatedResponse<>(productPage.map(this::mapToProductResponse));
    }

    @Override
    public List<Product> findByCategory(String category) {
        return null;
    }

    @Override
    public Product update(UUID id, ProductDto productDtoUpdate) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        Product productExistCode = productRepository.findByCode(productDtoUpdate.getCode().toUpperCase());

        if (productExistCode != null && !productExistCode.getId().toString().equals(id.toString())) {
            throw new ProductExistException("Product with code " + productDtoUpdate.getCode() + " already exists.");
        }

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setCode(productDtoUpdate.getCode().toUpperCase());
            existingProduct.setName(productDtoUpdate.getName());
            existingProduct.setCategory(productDtoUpdate.getCategory());
            existingProduct.setBranch(productDtoUpdate.getBranch());
            existingProduct.setType(productDtoUpdate.getType());
            existingProduct.setDescription(productDtoUpdate.getDescription());

            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setCode(product.getCode());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setBranch(product.getBranch());
        productResponse.setType(product.getType());
        productResponse.setDescription(product.getDescription());
        return productResponse;
    }
}
