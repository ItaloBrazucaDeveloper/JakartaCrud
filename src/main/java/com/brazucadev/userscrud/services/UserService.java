package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.repositories.UserRepository;
import com.brazucadev.userscrud.utils.Bcrypt;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private UserRepository userRepository = new UserRepository();

    public User setBcryptPassword(User user) {
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(Bcrypt.hashPassword(user.getPassword()));
        }
        return user;
    }

    @Override
    public List<User> list(Optional<String> id) { return userRepository.read(id); }

    @Override
    public boolean push(User user) {
        return this.userRepository.create(setBcryptPassword(user));
    }

    @Override
    public boolean refresh(User user) {
        return this.userRepository.update(setBcryptPassword(user));
    }

    @Override
    public boolean remove(String userId) {
        long id = Long.parseLong(userId);
        return this.userRepository.delete(id);
    }
}
