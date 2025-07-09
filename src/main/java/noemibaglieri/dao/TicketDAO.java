

package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Ticket;
import noemibaglieri.exceptions.TicketNotFoundException;

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
        System.out.println("ticket salvato");
    }

    public Ticket findTicketById(Long id) {
        Ticket found = entityManager.find(Ticket.class, id);
        if (found == null) {
            throw new TicketNotFoundException(id);

        }
        return found;
    }

}


