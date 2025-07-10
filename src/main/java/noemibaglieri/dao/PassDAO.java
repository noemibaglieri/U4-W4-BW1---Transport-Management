package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Pass;
import noemibaglieri.exceptions.InvalidPassException;
import noemibaglieri.exceptions.PassNotFoundException;

import java.time.LocalDate;

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
        { throw new PassNotFoundException(id);}

        return pass;
    }

    //metodo per verificare la validità di un pass tramite id
    // (verifico che la data di scadenza sia dopo la data odierna)



    public boolean isPassValid(Long passId) {
        Pass pass = entityManager.find(Pass.class, passId);
        if (pass == null) {
            System.out.println("Pass con ID " + passId + " non trovato.");
            throw new PassNotFoundException(passId);
        }

        boolean notExpired = pass.getEndDate().isAfter(LocalDate.now());

        if (!notExpired) {
            throw new InvalidPassException(passId);
        }

        System.out.println("Il pass con ID " + passId + " è valido.");
        return true;
    }

}

