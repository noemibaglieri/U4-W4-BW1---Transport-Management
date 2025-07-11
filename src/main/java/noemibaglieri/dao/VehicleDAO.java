package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Bus;
import noemibaglieri.entities.Ticket;
import noemibaglieri.entities.Tram;
import noemibaglieri.entities.Vehicle;

import java.util.List;

public class VehicleDAO {

    private EntityManager em;

    public VehicleDAO(EntityManager em) {
        this.em = em;
    }

    // Salva un veicolo
    public void save(Vehicle vehicle) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Trova un veicolo per ID
    public Vehicle find(Long id) {
        return em.find(Vehicle.class, id);
    }

    public String obliterateTicket(Long ticketId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ticket ticket = em.find(Ticket.class, ticketId);
            if (ticket == null) {
                tx.rollback();
                return "Ticket con ID " + ticketId + " non trovato.";
            }
            if (ticket.isValidated()) {
                tx.rollback();
                return "Ticket " + ticketId + " è già stato obliterato.";
            }

            Vehicle vehicle = ticket.getVehicle();
            if (vehicle == null) {
                tx.rollback();
                return "Errore: il ticket non è associato né a un bus né a un tram.";
            }

            String mezzo;
            if (vehicle instanceof Bus) {
                mezzo = "bus";
            } else if (vehicle instanceof Tram) {
                mezzo = "tram";
            } else {

                tx.rollback();
                return "Errore interno: tipo di veicolo non riconosciuto.";
            }

            // Obliterazione
            ticket.setValidated(true);
            ticket.setVehicle(null);

            em.merge(ticket);
            tx.commit();
            return "Ticket " + ticketId + " obliterato su " + mezzo + ".";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return "Errore durante l'obliterazione del ticket.";
        }
    }

    public List<Vehicle> findAll() {
        return em.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
    }


}

