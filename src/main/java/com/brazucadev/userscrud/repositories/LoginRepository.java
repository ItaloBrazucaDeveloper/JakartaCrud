package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import com.brazucadev.userscrud.utils.Bcrypt;
import jakarta.persistence.*;

import java.util.Optional;

public class LoginRepository extends Repository implements ILoginRepository {
	@Override
	public Optional<User> checkCredentials(String email, String password) {
		try {
			User user = this.getEntityManager()
				.createQuery("SELECT u FROM Users u WHERE u.email = :email", User.class)
				.setParameter("email", email)
				.getSingleResult();

			boolean isPasswordValid = Bcrypt.checkPassword(password, user.getPassword());
			if (isPasswordValid) return Optional.of(user);

			return Optional.empty();
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
}
