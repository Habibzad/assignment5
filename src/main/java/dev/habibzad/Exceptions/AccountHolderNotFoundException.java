package dev.habibzad.Exceptions;

public class AccountHolderNotFoundException extends RuntimeException{
    public AccountHolderNotFoundException(String message) {
        super(message);
    }
}
