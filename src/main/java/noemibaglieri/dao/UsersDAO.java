package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.User;
import noemibaglieri.exceptions.UserNotFoundException;

public class UsersDAO {
    private final EntityManager entityManager;

    public UsersDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User newUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUser);
        transaction.commit();
        System.out.println("The user * " + newUser.getName() + " " + newUser.getSurname() + " * was successfully registered.");
    }

    public User findById(Long userId) {
        User found = entityManager.find(User.class, userId);
        if (found == null) throw new UserNotFoundException(userId);
        return found;
    }
}