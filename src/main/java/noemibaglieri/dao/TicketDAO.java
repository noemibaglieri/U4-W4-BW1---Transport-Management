

package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Ticket;

public class TicketDAO {

    private final EntityManager entityManager;

    public TicketDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //prima di salvare i biglietti nel db devo avere un vendor e un vehicle salvati
    public void save(Ticket ticket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ticket);
        transaction.commit();
    }

    public Ticket findTicketById(Long id) {
        return entityManager.find(Ticket.class, id);
    }

}


