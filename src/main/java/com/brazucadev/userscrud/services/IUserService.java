package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> list(Optional<String> id);
    boolean push(User user);
    boolean refresh(User user);
    boolean remove(long userId);
}
