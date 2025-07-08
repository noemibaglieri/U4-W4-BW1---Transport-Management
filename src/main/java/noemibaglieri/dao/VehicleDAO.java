package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Vehicle;

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
}

