package com.brazucadev.userscrud.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public abstract class Repository {
	private static EntityManager emf = null;

	protected static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence
				.createEntityManagerFactory("my-persistence-unit")
				.createEntityManager();
		}
		return emf;
	}
}
