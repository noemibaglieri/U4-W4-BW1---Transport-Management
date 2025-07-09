package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Pass;
import noemibaglieri.exceptions.PassNotFoundException;

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
        System.out.println("pass salvato");
    }

    public Pass findPassById(Long id) {
        Pass pass =  entityManager.find(Pass.class, id);
        if (pass == null)
        { throw new PassNotFoundException(id);
        }

        return pass;
    }
    }

