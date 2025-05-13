package com.brazucadev.userscrud.repositories;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public abstract class Repository {
	private static final Dotenv dotenv = getDotenv();
	private static final EntityManagerFactory emf = init();

	private static EntityManagerFactory init() {
		Map<String, String> properties = new HashMap<>();

		properties.put("jakarta.persistence.jdbc.url", dotenv.get("DB_URL"));
		properties.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
		properties.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASS"));

		return Persistence.createEntityManagerFactory("my-persistence-unit", properties);
	}

	protected static EntityManager getEntityManager() { return emf.createEntityManager(); }

	private static Dotenv getDotenv() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		rootPath = rootPath.substring(0, rootPath.indexOf("/target"));

		return Dotenv.configure()
			.directory(rootPath)
			.filename(".env")
			.load();
	}
}
