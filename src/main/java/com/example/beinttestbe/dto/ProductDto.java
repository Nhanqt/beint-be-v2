package com.example.beinttestbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private String category;
    private String branch;
    private String type;
    private String description;
}
