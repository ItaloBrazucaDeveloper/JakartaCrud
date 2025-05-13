package com.brazucadev.userscrud.repositories;

import com.brazucadev.userscrud.entities.User;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends Repository implements IUserRepository {
    @Override
    public List<User> read(Optional<String> id) {
	    try (EntityManager em = getEntityManager()) {
		    if (id.isPresent()) {
			    return em.createQuery("SELECT u FROM Users u WHERE u.id = :id", User.class)
				    .setParameter("id", Long.parseLong(id.get()))
				    .getResultList();
		    }
		    return em.createQuery("SELECT u FROM Users u", User.class)
			    .getResultList();
	    } catch (Exception ex) {
		    return new ArrayList<>();
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
                em.remove(user);
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
            if (isCreate) em.persist(user);
                else em.merge(user);
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