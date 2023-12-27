package com.abdisadykov.cryptocurrencymicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OperationNotFoundException.class)
    public Map<String, String> operationNotFound(OperationNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", exception.getMessage());
        return error;
    }


}
