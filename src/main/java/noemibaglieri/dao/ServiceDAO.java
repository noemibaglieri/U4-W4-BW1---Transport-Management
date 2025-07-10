package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import noemibaglieri.entities.Maintenance;
import noemibaglieri.entities.Service;
import noemibaglieri.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;

public class ServiceDAO {

    private EntityManager em;

    public ServiceDAO(EntityManager em) {
        this.em = em;
    }

    // Salva un nuovo periodo di servizio
    public void save(Service service) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(service);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Trova per ID
    public Service findById(Long id) {
        return em.find(Service.class, id);
    }

    // Elimina un periodo di servizio
    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Service service = em.find(Service.class, id);
            if (service != null) {
                em.remove(service);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


    public boolean isVehicleInService(Vehicle vehicle, LocalDate date) {
        // Verifica se è in manutenzione
        boolean isInMaintenance = isVehicleInMaintenanceOnDate(vehicle, date);

        // Se è in manutenzione, NON è in servizio
        // Se non è in manutenzione, È automaticamente in servizio
        return !isInMaintenance;
    }

    // Metodo helper per verificare se un veicolo è in manutenzione in una specifica data
    private boolean isVehicleInMaintenanceOnDate(Vehicle vehicle, LocalDate date) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(m) FROM Maintenance m " +
                        "WHERE m.vehicle = :vehicle AND :date BETWEEN m.startDate AND m.endDate",
                Long.class
        );
        query.setParameter("vehicle", vehicle);
        query.setParameter("date", date);
        return query.getSingleResult() > 0;
    }

    // Restituisce lo status del veicolo
    public String getVehicleStatus(Vehicle vehicle, LocalDate date) {
        boolean isInMaintenance = isVehicleInMaintenanceOnDate(vehicle, date);

        if (isInMaintenance) {
            return "IN MANUTENZIONE";
        } else {
            return "IN SERVIZIO";
        }
    }

    // Restituisce tutti i veicoli in servizio in una certa data
    public List<Vehicle> getVehiclesInService(LocalDate date) {
        // Prendi tutti i veicoli che NON sono in manutenzione
        TypedQuery<Vehicle> query = em.createQuery(
                "SELECT DISTINCT v FROM Vehicle v " +
                        "WHERE v NOT IN (" +
                        "    SELECT m.vehicle FROM Maintenance m " +
                        "    WHERE :date BETWEEN m.startDate AND m.endDate" +
                        ")",
                Vehicle.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }

    // Restituisce tutti i veicoli in manutenzione in una certa data
    public List<Vehicle> getVehiclesInMaintenance(LocalDate date) {
        TypedQuery<Vehicle> query = em.createQuery(
                "SELECT DISTINCT m.vehicle FROM Maintenance m " +
                        "WHERE :date BETWEEN m.startDate AND m.endDate",
                Vehicle.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }


    // Restituisce tutti i periodi di servizio per un veicolo
    public List<Service> findByVehicle(Vehicle vehicle) {
        TypedQuery<Service> query = em.createQuery(
                "SELECT s FROM Service s WHERE s.vehicle = :vehicle ORDER BY s.startDate DESC",
                Service.class
        );
        query.setParameter("vehicle", vehicle);
        return query.getResultList();
    }

    // Restituisce tutti i periodi attivi in una certa data
    public List<Service> findActiveServicesOnDate(LocalDate date) {
        TypedQuery<Service> query = em.createQuery(
                "SELECT s FROM Service s WHERE :date BETWEEN s.startDate AND s.endDate",
                Service.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }
}