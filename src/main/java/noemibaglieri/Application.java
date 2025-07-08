package noemibaglieri;

import noemibaglieri.entities.Maintenance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.dao.MaintenanceDAO;
import noemibaglieri.dao.VehicleDAO;
import noemibaglieri.entities.Bus;
import noemibaglieri.entities.Tram;
import noemibaglieri.entities.Vehicle;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // <-- crei EntityManager
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);

        // Crea un nuovo Bus

        Bus bus1 = new Bus("AB200",40, true);
        Bus bus2 = new Bus("AB202",40, true);
        Bus bus3 = new Bus("AB203",40, true);
        Bus bus4 = new Bus("AB204",40, true);
        Bus bus5 = new Bus("AB205",40, true);

        Tram tram1 = new Tram("TR300", 100, true);
        Tram tram2 = new Tram("TR301", 90, true);
        Tram tram3 = new Tram("TR302", 120, false);
        Tram tram4 = new Tram("TR303", 110, true);
        Tram tram5 = new Tram("TR304", 100, true);

// Salva il veicolo nel database
        vehicleDAO.save(tram1);
        vehicleDAO.save(tram2);
        vehicleDAO.save(tram3);
        vehicleDAO.save(tram4);
        vehicleDAO.save(tram5);


        vehicleDAO.save(bus1);
        vehicleDAO.save(bus2);
        vehicleDAO.save(bus3);
        vehicleDAO.save(bus4);
        vehicleDAO.save(bus5);

        Bus[] buses = {bus1, bus2, bus3, bus4, bus5};

        // Recupera il veicolo dal database usando il suo ID
        for (Bus bus : buses) {
            Vehicle foundVehicle = vehicleDAO.find(bus.getVehicleId());
            System.out.println("Veicolo trovato: " + foundVehicle);
        }

        for (Bus bus : buses) {
            Maintenance manut = new Maintenance(
                    bus,
                    LocalDate.now(),
                    LocalDate.now().plusDays(3),
                    "Controllo freni"
            );
            maintenanceDAO.save(manut);
            System.out.println("Maintenance salvata con ID: " + manut.getMaintenanceId());

            Maintenance foundMaint = maintenanceDAO.find(manut.getMaintenanceId());
            System.out.println("Maintenance trovata: " + foundMaint);
        }

        em.close();
        emf.close();
    }
}
