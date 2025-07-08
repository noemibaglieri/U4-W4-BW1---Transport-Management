package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;

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



        VendorsDAO vd = new VendorsDAO(em);
        UsersDAO ud = new UsersDAO(em);

        HumanVendor hv1 = new HumanVendor("Tabaccheria Christian", LocalTime.of(9, 30), LocalTime.of(20,0));
        HumanVendor hv2 = new HumanVendor("Tabaccheria New Cart", LocalTime.of(7, 30), LocalTime.of(17,0));
        MachineVendor hv3 = new MachineVendor("Macchinetta Via Dei Volsci", true);
        MachineVendor mv1 = new MachineVendor("Macchinetta Viale Le Corbusier", true);
        MachineVendor mv2 = new MachineVendor("Macchinetta Via Tiziano", false);

        // Salva il vendor nel database

        vd.save(hv1);
        vd.save(hv2);
        vd.save(hv3);
        vd.save(mv1);
        vd.save(mv2);


        User user1 = new User("Daenerys", "Targaryen", LocalDate.of(1995,1,14));
        User user2 = new User("Cersei", "Lannister", LocalDate.of(1978, 9, 11));
        User user3 = new User("Obara", "Martell", LocalDate.of(2001, 3, 17));
        User user4 = new User("Olenna", "Tyrell", LocalDate.of(1940, 12, 31));
        User user5 = new User("Arya", "Stark", LocalDate.of(2004, 12, 11));


        ud.save(user1);
        ud.save(user2);
        ud.save(user3);
        ud.save(user4);
        ud.save(user5);


        CardsDAO cd = new CardsDAO(em);//vendors

        // recuper0 utenti dal DB
        User user1DB = ud.findById(1L);
        User user2DB = ud.findById(2L);
        User user3DB = ud.findById(3L);
        User user4DB = ud.findById(4L);
        User user5DB = ud.findById(5L);

        // recupero vendors dal DB
        Vendor vendor1 = vd.findById(1L);
        Vendor vendor2 = vd.findById(2L);
        Vendor vendor3 = vd.findById(3L);
        Vendor vendor4 = vd.findById(4L);
        Vendor vendor5 = vd.findById(5L);

        // creo cards
        Card card1 = new Card(LocalDate.of(2024, 7, 1), user1);
        Card card2 = new Card(LocalDate.of(2024, 8, 1), user2);
        Card card3 = new Card(LocalDate.of(2024, 6, 15), user3);
        Card card4 = new Card(LocalDate.of(2024, 5, 20), user4);
        Card card5 = new Card(LocalDate.of(2024, 7, 5), user5);

        cd.save(card1);
        cd.save(card2);
        cd.save(card3);
        cd.save(card4);
        cd.save(card5);

        em.close();
        emf.close();
    }
}
