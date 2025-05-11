package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public List<User> list() {
        return userRepository.read();
    }

    @Override
    public boolean push(User user) {
        return this.userRepository.create(user);
    }

    @Override
    public boolean refresh(User user) {
        return false;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }
}
