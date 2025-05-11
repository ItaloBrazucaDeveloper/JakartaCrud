package com.brazucadev.userscrud.entities;

public class UserBuilder {
	// Atributos da classe User
	long id = 0;
	String name = "";
	String email = "";
	String password = "";
	Integer role = 0;

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
		return new User(name, email, password, role);
	}
}
