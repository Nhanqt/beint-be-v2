package com.example.beinttestbe.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class CustomErrorResponse {
    private String message;
    private String details;
    private HttpStatus status;

    public CustomErrorResponse(String message, String details, HttpStatus status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

}
