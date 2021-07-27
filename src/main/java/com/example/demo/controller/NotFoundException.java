package com.example.demo.controller;

public class NotFoundException extends RuntimeException {
    private String messageError;
    public NotFoundException(String messageError) {
        super(messageError);
        this.messageError = messageError;
    }
}