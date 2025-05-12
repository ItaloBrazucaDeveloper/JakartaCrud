package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    public List<User> read(Optional<String> id);
    public boolean create(User user);
    public boolean update(User user);
    public boolean delete(long userId);
}
