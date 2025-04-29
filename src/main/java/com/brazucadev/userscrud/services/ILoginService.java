package com.brazucadev.userscrud.services;

import jakarta.servlet.http.HttpSession;

public interface ILoginService {
	abstract public boolean login(String email, String password, HttpSession session);
}
