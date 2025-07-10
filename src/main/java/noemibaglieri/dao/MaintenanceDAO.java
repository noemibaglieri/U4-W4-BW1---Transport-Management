package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import noemibaglieri.entities.Maintenance;
import noemibaglieri.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;

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
            if (maintenance.getMaintenanceId() == null) {
                em.persist(maintenance); // Nuovo oggetto, persisti
            } else {
                em.merge(maintenance);   // Oggetto esistente, fai merge
            }
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


    // Restituisce tutte le manutenzioni associate a un veicolo
    public List<Maintenance> findByVehicle(Vehicle vehicle) {
        TypedQuery<Maintenance> query = em.createQuery(
                "SELECT m FROM Maintenance m WHERE m.vehicle = :vehicle ORDER BY m.startDate DESC",
                Maintenance.class
        );
        query.setParameter("vehicle", vehicle);
        return query.getResultList();
    }

    // Verifica se un veicolo è attualmente in manutenzione
    public boolean isVehicleInMaintenance(Vehicle vehicle) {
        TypedQuery<Maintenance> query = em.createQuery(
                "SELECT m FROM Maintenance m " +
                        "WHERE m.vehicle = :vehicle AND :today BETWEEN m.startDate AND m.endDate",
                Maintenance.class
        );
        query.setParameter("vehicle", vehicle);
        query.setParameter("today", LocalDate.now());

        List<Maintenance> results = query.getResultList();

        for (Maintenance m : results) {
            System.out.println("Veicolo in manutenzione dal " + m.getStartDate() + " al " + m.getEndDate() +
                    " (causa: " + m.getMaintenanceCause() + ")");
        }

        return !results.isEmpty();
    }


    // Restituisce tutte le manutenzioni attive in una certa data
    public List<Maintenance> findActiveMaintenancesOnDate(LocalDate date) {
        TypedQuery<Maintenance> query = em.createQuery(
                "SELECT m FROM Maintenance m WHERE :date BETWEEN m.startDate AND m.endDate",
                Maintenance.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }


}
