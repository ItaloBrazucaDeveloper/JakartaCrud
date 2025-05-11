package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class LoginRepository implements ILoginRepository {
	private static final EntityManagerFactory emf
		= Persistence.createEntityManagerFactory("my-persistence-unit");
	private final EntityManager entityManager;


	public LoginRepository() {
		this.entityManager = emf.createEntityManager();
	}

	@Override
	public Optional<User> validate(String email, String password) {
		try {
			String query = """
				SELECT u FROM User u WHERE u.email = :email AND u.password = :password
			""";

			User user = entityManager
				.createQuery(query, User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();

			return Optional.of(user);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public void close() {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
