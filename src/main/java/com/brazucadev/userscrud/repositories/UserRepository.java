package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<User> read() {
        String query = "SELECT u FROM User u";

        return this.getEntityManager()
          .createQuery(query, User.class)
          .getResultList();
    }

    @Override
    public boolean create(User user) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user); // JPA sabe transformar User em INSERT
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }
}