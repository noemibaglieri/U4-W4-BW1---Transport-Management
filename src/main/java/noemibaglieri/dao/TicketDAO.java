

package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Ticket;

public class TicketDAO {

    private final EntityManager entityManager;

    public TicketDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Ticket ticket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ticket);
        transaction.commit();
    }
}


