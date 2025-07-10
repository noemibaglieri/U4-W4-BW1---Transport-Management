

package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import noemibaglieri.entities.Ticket;
import noemibaglieri.exceptions.TicketNotFoundException;

import java.util.List;

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

    public long countValidatedTickets() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.isValidated = true", Long.class
        );
        return query.getSingleResult();
    }

    public long countNonValidatedTickets() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.isValidated = false", Long.class
        );
        return query.getSingleResult();
    }

    public List<Ticket> findPendingTickets() {
        TypedQuery<Ticket> query = entityManager.createQuery(
                "SELECT t FROM Ticket t WHERE t.isValidated = false", Ticket.class
        );
        return query.getResultList();
    }


    public void update(Ticket ticket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(ticket);
        transaction.commit();
    }



}


