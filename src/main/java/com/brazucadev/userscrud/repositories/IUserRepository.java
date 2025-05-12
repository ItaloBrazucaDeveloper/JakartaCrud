package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> read(Optional<String> id);
    boolean create(User user);
    boolean update(User user);
    boolean delete(long userId);
}
