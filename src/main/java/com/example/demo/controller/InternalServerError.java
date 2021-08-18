package com.example.demo.controller;

import java.rmi.ServerException;

public class InternalServerError extends RuntimeException{
    public InternalServerError(int i) throws ServerException {
        throw new ServerException("505");
    }
}
