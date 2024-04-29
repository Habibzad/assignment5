package dev.habibzad.controllers;

import dev.habibzad.Exceptions.DaoException;
import dev.habibzad.dao.AccountHolderDao;
import dev.habibzad.dao.UserDao;
import dev.habibzad.dto.CreateProfileDto;
import dev.habibzad.models.AccountHolder;
import dev.habibzad.security.dto.LoginDto;
import dev.habibzad.security.dto.LoginResponseDto;
import dev.habibzad.security.dto.RegisterUserDto;
import dev.habibzad.security.jwt.TokenProvider;
import dev.habibzad.security.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/account-holders")
@PreAuthorize("isAuthenticated()")
public class AccountHolderController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AccountHolderDao accountHolderDao;
    private final UserDao userDao;

    public AccountHolderController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AccountHolderDao accountHolderDao, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountHolderDao = accountHolderDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody @Valid CreateProfileDto createProfileDto){
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setFirstName(createProfileDto.getFirstName());
        accountHolder.setLastName(createProfileDto.getLastName());
        accountHolder.setSsn(createProfileDto.getSsn());
        accountHolder.setEmail(createProfileDto.getEmail());
        accountHolder.setPhone(createProfileDto.getPhone());
        accountHolder.setAddress(createProfileDto.getAddress());
        accountHolder.setUsername(createProfileDto.getUsername());
        accountHolderDao.addAccountHolder(accountHolder);
        RegisterUserDto newUser = new RegisterUserDto();
        newUser.setUsername(createProfileDto.getUsername());
        newUser.setPassword(createProfileDto.getPassword());
        try {
            User user = userDao.createUser(newUser);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
            }
            return new ResponseEntity<>("Account created successfully!", HttpStatus.CREATED);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }
    @PreAuthorize("permitAll()")
    @PostMapping(path = "/login")
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user;
        try {
            user = userDao.getUserByUsername(loginDto.getUsername());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }

        return new LoginResponseDto(jwt, accountHolderDao.findAccountHolderByUsername(loginDto.getUsername()));
    }
}
