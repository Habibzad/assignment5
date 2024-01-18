package dev.habibzad.dao;


import dev.habibzad.security.dto.RegisterUserDto;
import dev.habibzad.security.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
