package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface ILoginService {
	Optional<User> validate(String email, String password);
	void setUserSession(HttpSession session, User user);
}
