package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> list(Optional<String> id);
    public boolean push(User user);
    public boolean refresh(User user);
    public boolean remove(long userId);
}
