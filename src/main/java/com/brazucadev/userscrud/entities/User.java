package com.brazucadev.userscrud.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int role;

    public User() {}

    public User(String name, String email, String password, Integer role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Métodos setters que retornam a própria instância (this)
    public User setId(long id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(int role) {
        this.role = role;
        return this;
    }

    // Método build que retorna a instância construída
    public User build() {
        return this;
    }

    // Getters para acessar os atributos
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
}