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

	public boolean login(String email, String password, HttpSession session) {
		Optional<User> userOptional = repository.validate(email, password);
		boolean isValidLogin = userOptional.isPresent();

		if (isValidLogin) {
			User user = userOptional.get();
			session.setAttribute("userRole", user.getRole());
			session.setAttribute("userName", user.getName());
		}

		return isValidLogin;
	}
}
