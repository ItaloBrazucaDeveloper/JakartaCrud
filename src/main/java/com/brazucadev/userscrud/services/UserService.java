package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.repositories.UserRepository;
import com.brazucadev.userscrud.utils.Bcrypt;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final UserRepository userRepository = new UserRepository();

    public String setBcryptPassword(String password) {
        if (password != null && !password.startsWith("$2a$")) {
            return Bcrypt.hashPassword(password);
        }
        return null;
    }

    @Override
    public List<User> list(Optional<String> id) { return userRepository.read(id); }

    @Override
    public boolean push(User user) {
        String hashedPassowrd = setBcryptPassword(user.getPassword());
        if (hashedPassowrd != null) user.setPassword(hashedPassowrd);
        return this.userRepository.create(user);
    }

    @Override
    public boolean refresh(User user) {
        String hashedPassowrd = setBcryptPassword(user.getPassword());
        if (hashedPassowrd != null) user.setPassword(hashedPassowrd);
        return this.userRepository.update(user);
    }

    @Override
    public boolean remove(String userId) {
        long id = Long.parseLong(userId);
        return this.userRepository.delete(id);
    }
}
