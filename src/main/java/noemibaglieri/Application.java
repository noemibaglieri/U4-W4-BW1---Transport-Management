package noemibaglieri;

import noemibaglieri.entities.Maintenance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.dao.MaintenanceDAO;
import noemibaglieri.dao.VehicleDAO;
import noemibaglieri.entities.Bus;
import noemibaglieri.entities.Vehicle;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // <-- crei EntityManager
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);

        // Crea un nuovo Bus
        Bus bus = new Bus("AB200",40, true);


        // Salva il veicolo nel database
        vehicleDAO.save(bus);

        // Recupera il veicolo dal database usando il suo ID
        Vehicle foundVehicle = vehicleDAO.find(bus.getVehicleId());
        System.out.println("Veicolo trovato: " + foundVehicle);

        Maintenance manut = new Maintenance(
                bus,                            // veicolo collegato
                LocalDate.now(),                // startDate
                LocalDate.now().plusDays(3),    // endDate
                "Controllo freni"               // causa
        );
        maintenanceDAO.save(manut);
        System.out.println("Maintenance salvata con ID: " + manut.getMaintenanceId());



        Maintenance foundMaint = maintenanceDAO.find(manut.getMaintenanceId());
        System.out.println("Maintenance trovata: " + foundMaint);

        em.close();
        emf.close();
    }
}
