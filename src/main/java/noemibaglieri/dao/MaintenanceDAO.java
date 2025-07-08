package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Maintenance;

public class MaintenanceDAO {

    private EntityManager em;

    public MaintenanceDAO(EntityManager em) {
        this.em = em;
    }

    // Salva una manutenzione nel database
    public void save(Maintenance maintenance) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(maintenance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Cerca una manutenzione per ID
    public Maintenance find(Long id) {
        return em.find(Maintenance.class, id);
    }
}
