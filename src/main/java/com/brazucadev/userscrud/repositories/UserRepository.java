package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    private static final EntityManagerFactory emf
      = Persistence.createEntityManagerFactory("my-persistence-unit");;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<User> read(Optional<String> id) {
        EntityManager em = getEntityManager();

        try {
            if (id.isPresent()) {
                return em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                  .setParameter("id", Long.parseLong(id.get()))
                  .getResultList();
            }
            return em.createQuery("SELECT u FROM User u", User.class)
              .getResultList();
        } catch(Exception ex) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean create(User user) { return persistEntity(user, true); }

    @Override
    public boolean update(User user) { return persistEntity(user, false); }

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
            return false;
        } finally {
            em.close();
        }
    }

    private boolean persistEntity(User user, boolean isCreate) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (isCreate) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
}