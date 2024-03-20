package com.example.beinttestbe.response;

import com.example.beinttestbe.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private UUID id;
    private String code;
    private String name;
    private String category;
    private String branch;
    private String type;
    private String description;

    public static ProductResponse fromProduct(Product product) {
        ProductResponse response = new ProductResponse();
        response.setCode(product.getCode());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setBranch(product.getBranch());
        response.setType(product.getType());
        response.setDescription(product.getDescription());
        return response;
    }
}
