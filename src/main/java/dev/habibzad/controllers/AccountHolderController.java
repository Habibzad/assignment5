package dev.habibzad.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-holders")
public class AccountHolderController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Account Holders", HttpStatus.I_AM_A_TEAPOT);
    }
}
