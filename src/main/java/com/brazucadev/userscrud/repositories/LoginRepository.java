package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.Optional;

@ApplicationScoped
public class LoginRepository implements ILoginRepository {
	private static final EntityManagerFactory emf
		= Persistence.createEntityManagerFactory("my-persistence-unit");

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public Optional<User> validate(String email, String password) {
		try {
			String query = """
				SELECT u FROM User u WHERE u.email = :email AND u.password = :password
			""";

			User user = this.getEntityManager()
				.createQuery(query, User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();

			return Optional.of(user);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
}
