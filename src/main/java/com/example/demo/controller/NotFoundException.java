package com.example.demo.controller;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messageError) {
        super(messageError);
    }
}