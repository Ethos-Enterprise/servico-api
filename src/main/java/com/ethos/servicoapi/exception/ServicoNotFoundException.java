package com.ethos.servicoapi.exception;

public class ServicoNotFoundException extends RuntimeException{
    public ServicoNotFoundException(String message) {
        super(message);
    }
}
