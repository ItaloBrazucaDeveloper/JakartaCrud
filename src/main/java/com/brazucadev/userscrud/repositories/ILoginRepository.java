package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;

import java.util.Optional;

public interface ILoginRepository {
	Optional<User> validate(String email, String password);
}
