package com.meritamerica.assignment5.controllers;


import com.meritamerica.assignment5.Exceptions.NoSuchAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchAccountException.class})
    public ResponseEntity<String> handleAccountHolderNotFoundException(NoSuchAccountException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
