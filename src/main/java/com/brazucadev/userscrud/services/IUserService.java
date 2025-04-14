package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;

import java.util.List;

public interface IUserService {
    public List<User> list();
    public boolean push(User user);
    public boolean refresh(User user);
    public boolean remove(User user);
}
