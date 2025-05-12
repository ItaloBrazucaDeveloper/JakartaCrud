package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserRepository implements IUserRepository {
    private static final EntityManagerFactory emf
      = Persistence.createEntityManagerFactory("my-persistence-unit");

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
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user); // Remove a entidade
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}