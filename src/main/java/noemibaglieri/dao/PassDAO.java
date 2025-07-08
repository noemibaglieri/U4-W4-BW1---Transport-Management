package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Pass;

public class PassDAO {

    private final EntityManager entityManager;

    public PassDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Pass pass) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(pass);
        transaction.commit();
    }

    public Pass findPassById(Long id) {
        return entityManager.find(Pass.class, id);
    }
}
