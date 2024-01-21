package dev.habibzad.security.dto;

import dev.habibzad.models.AccountHolder;
import dev.habibzad.security.model.User;
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
public class LoginResponseDto {

    private String token;
    private AccountHolder accountHolder;

    public LoginResponseDto(String token, AccountHolder accountHolder) {
        this.token = token;
        this.accountHolder = accountHolder;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

}
