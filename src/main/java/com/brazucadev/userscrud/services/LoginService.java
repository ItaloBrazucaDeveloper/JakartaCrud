package com.brazucadev.userscrud.services;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.repositories.ILoginRepository;
import com.brazucadev.userscrud.repositories.LoginRepository;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginService implements ILoginService {
	ILoginRepository repository;

	public LoginService() {
		this.repository = new LoginRepository();
	}

	public Optional<User> validate(String email, String password) {
		return repository.checkCredentials(email, password);
	}

	public void setUserSession(HttpSession session, User user) {
		session.setAttribute("userRole", user.getRole());
		session.setAttribute("userId", user.getId());
		session.setAttribute("userName", user.getName());
	}
}
