package com.example.beinttestbe.exception;
import com.example.beinttestbe.response.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<Object> handleProductExistException(ProductExistException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Conflict", ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
