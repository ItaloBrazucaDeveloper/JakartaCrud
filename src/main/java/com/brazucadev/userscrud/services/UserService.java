package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.repositories.UserRepository;
import com.brazucadev.userscrud.utils.Bcrypt;

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
        // Só faz o hash se ainda não estiver hasheada
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(Bcrypt.hashPassword(user.getPassword()));
        }
        return this.userRepository.create(user);
    }

    @Override
    public boolean refresh(User user) {
        return false;
    }

    @Override
    public boolean remove(long userId) {
        return this.userRepository.delete(userId);
    }
}
