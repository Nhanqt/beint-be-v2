package com.example.beinttestbe.controller;


import com.example.beinttestbe.dto.ProductDto;
import com.example.beinttestbe.entity.Product;
import com.example.beinttestbe.exception.ProductExistException;
import com.example.beinttestbe.exception.ProductNotFoundException;
import com.example.beinttestbe.response.CustomErrorResponse;
import com.example.beinttestbe.response.ProductResponse;
import com.example.beinttestbe.service.ProductService;
import com.example.beinttestbe.utils.PaginatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        try {
            Product savedProduct = productService.save(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (ProductExistException e) {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Conflict", e.getMessage(), HttpStatus.CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the product.");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<PaginatedResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginatedResponse<ProductResponse> response = productService.findAll(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") UUID id,
            @RequestBody ProductDto productDtoUpdate) {
        try {
            Product updatedProduct = productService.update(id, productDtoUpdate);
            return ResponseEntity.ok(ProductResponse.fromProduct(updatedProduct));
        } catch (ProductExistException e) {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Conflict", e.getMessage(), HttpStatus.CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }  catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
