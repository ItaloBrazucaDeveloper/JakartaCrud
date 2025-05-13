package com.brazucadev.userscrud.utils;

import com.brazucadev.userscrud.entities.User;

public class UserBuilder {
	// Atributos da classe User
	private long id = 0;
	private String name = "";
	private String email = "";
	private String password = "";
	private Integer role = 0;

	// Instância da classe User
	User user;

	public UserBuilder() {
		this.user = new User();
	}

	// Métodos setters que retornam a própria instância (this)
	public UserBuilder withId(long id) {
		this.id = id;
		return this;
	}

	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder withRole(int role) {
		this.role = role;
		return this;
	}

	// Método build que retorna a instância construída
	public User build() {
		return new User(this.id, this.name, this.email, this.password, this.role);
	}
}
