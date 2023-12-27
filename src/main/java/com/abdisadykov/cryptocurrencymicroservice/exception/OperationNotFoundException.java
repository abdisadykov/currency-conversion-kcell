package com.abdisadykov.cryptocurrencymicroservice.exception;

public class OperationNotFoundException extends RuntimeException {

    public OperationNotFoundException(String message) {
        super(message);
    }

}
