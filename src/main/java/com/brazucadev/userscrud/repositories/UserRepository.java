package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final EntityManagerFactory emf
      = Persistence.createEntityManagerFactory("my-persistence-unit");
    private final EntityManager entityManager;

    public UserRepository() {
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<User> read() {
        String query = "SELECT u FROM User u";

        return entityManager
          .createQuery(query, User.class)
          .getResultList();
    }

    @Override
    public void create(User user) {
        
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }
}