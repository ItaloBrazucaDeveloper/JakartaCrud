package com.brazucadev.userscrud.entities;

public class User {
    public long id;
    public String name;
    public String email;
    public String password;
    public int role;

    public User() {}

    public User(
            long id,
            String name,
            String email,
            String password,
            int role
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}