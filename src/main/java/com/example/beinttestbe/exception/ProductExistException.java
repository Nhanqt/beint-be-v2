package com.example.beinttestbe.exception;

public class ProductExistException extends RuntimeException{
    public ProductExistException(String message) {
        super(message);
    }
}
