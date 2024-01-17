package com.meritamerica.assignment5.Exceptions;

public class AccountHolderNotFoundException extends RuntimeException{
    public AccountHolderNotFoundException(String message) {
        super(message);
    }
}
