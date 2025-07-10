package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.enums.PassType;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // <-- crei EntityManager
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);
        ServiceDAO serviceDAO = new ServiceDAO(em);




        // Creo un nuovo Bus

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

        // Salvo il veicolo nel database
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

        for (Bus bus : (buses)) {
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

        Maintenance manut2 = new Maintenance(bus1,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                "Controllo freni"
        );
        maintenanceDAO.save(manut2);

        Maintenance manut3 = new Maintenance(bus1,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                "Controllo freni"
        );
        maintenanceDAO.save(manut3);

        maintenanceDAO.save(manut2);
        maintenanceDAO.save(manut3);



        VendorsDAO vd = new VendorsDAO(em);
        UsersDAO ud = new UsersDAO(em);

        HumanVendor hv1 = new HumanVendor("Tabaccheria Christian", LocalTime.of(9, 30), LocalTime.of(20,0));
        HumanVendor hv2 = new HumanVendor("Tabaccheria New Cart", LocalTime.of(7, 30), LocalTime.of(17,0));
        MachineVendor hv3 = new MachineVendor("Macchinetta Via Dei Volsci", true);
        MachineVendor mv1 = new MachineVendor("Macchinetta Viale Le Corbusier", true);
        MachineVendor mv2 = new MachineVendor("Macchinetta Via Tiziano", false);

        // Salvo il vendor nel database

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


        CardsDAO cd = new CardsDAO(em);

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
        Card card1 = new Card(LocalDate.of(2025, 7, 1), user1);
        Card card2 = new Card(LocalDate.of(2025, 8, 1), user2);
        Card card3 = new Card(LocalDate.of(2025, 6, 15), user3);
        Card card4 = new Card(LocalDate.of(2025, 5, 20), user4);
        Card card5 = new Card(LocalDate.of(2025, 7, 5), user5);

        cd.save(card1);
        cd.save(card2);
        cd.save(card3);
        cd.save(card4);
        cd.save(card5);

        PassDAO pd = new PassDAO(em);

        // Recupero card  dal DB
        Card c1 = cd.findById(1L);
        Card c2 = cd.findById(2L);
        Card c3 = cd.findById(3L);
        Card c4 = cd.findById(4L);
        Card c5 = cd.findById(5L);

       //creo i pass
        LocalDate today = LocalDate.now();

        Pass pass1 = new Pass(c1, today, PassType.MONTHLY);
        pass1.setVendor(vendor1);

        Pass pass2 = new Pass(c2, today.minusDays(10), PassType.WEEKLY);
        pass2.setVendor(vendor2);

        Pass pass3 = new Pass(c3, today.minusDays(5), PassType.MONTHLY);
        pass3.setVendor(vendor3);

        Pass pass4 = new Pass(c4, today, PassType.WEEKLY);
        pass4.setVendor(vendor4);

        Pass pass5 = new Pass(c5, today.minusDays(20), PassType.MONTHLY);
        pass5.setVendor(vendor5);

       pd.save(pass1);
        pd.save(pass2);
        pd.save(pass3);
        pd.save(pass4);
        pd.save(pass5);

        RoutesDao rd = new RoutesDao(em);

// Creo delle route
        Route route1 = new Route("Centro", "Stazione", 35);
        Route route2 = new Route("Stazione", "Aeroporto", 50);
        Route route3 = new Route("Università", "Centro", 25);
        Route route4 = new Route("Ospedale", "Quartiere Nord", 40);
        Route route5 = new Route("Quartiere Sud", "Mercato", 30);


     rd.save(route1);
        rd.save(route2);
        rd.save(route3);
        rd.save(route4);
        rd.save(route5);

        VehicleTripDao td = new VehicleTripDao(em);

        // veicoli salvati dal db
        Vehicle v1 = vehicleDAO.find(1L);
        Vehicle v2 = vehicleDAO.find(2L);
        Vehicle v3 = vehicleDAO.find(3L);
        Vehicle v4 = vehicleDAO.find(4L);
        Vehicle v5 = vehicleDAO.find(5L);

        //route salvate dal db
        Route r1 = rd.findById(1L);
        Route r2 = rd.findById(2L);
        Route r3 = rd.findById(3L);
        Route r4 = rd.findById(4L);
        Route r5 = rd.findById(5L);


        VehicleTrip trip1 = new VehicleTrip(v1, r1, LocalDate.of(2025, 6, 8), 38);
        VehicleTrip trip2 = new VehicleTrip(v2, r2, LocalDate.of(2025, 5, 9), 52);
        VehicleTrip trip3 = new VehicleTrip(v3, r3, LocalDate.of(2025, 2, 10), 27);
        VehicleTrip trip4 = new VehicleTrip(v4, r4, LocalDate.of(2025, 4, 11), 41);
        VehicleTrip trip5 = new VehicleTrip(v5, r5, LocalDate.of(2025, 6, 12), 29);


        td.save(trip1);
        td.save(trip2);
        td.save(trip3);
        td.save(trip4);
        td.save(trip5);

        Ticket ticket1 = new Ticket(LocalDate.now(), 1.50, vendor1);
        Ticket ticket2 = new Ticket(LocalDate.now(), 1.50, vendor2);
        Ticket ticket3 = new Ticket(LocalDate.now(), 1.50, vendor3);
        Ticket ticket4 = new Ticket(LocalDate.now(), 1.50, vendor2);
        Ticket ticket5 = new Ticket(LocalDate.now(), 1.50, vendor5);

        ticketDAO.save(ticket1);
        ticketDAO.save(ticket2);
        ticketDAO.save(ticket3);
        ticketDAO.save(ticket4);
        ticketDAO.save(ticket5);


        ticket1.setVehicle(bus1);
        ticketDAO.save(ticket1);
        ticket2.setVehicle(tram1);
        ticketDAO.save(ticket2);



        String risultato = vehicleDAO.obliterateTicket(ticket1.getTicketId());
        System.out.println(risultato);



        String secondo = vehicleDAO.obliterateTicket(ticket2.getTicketId());
        System.out.println(secondo);

        String terzo = vehicleDAO.obliterateTicket(ticket2.getTicketId());
        System.out.println(terzo);

        Bus bus6 = new Bus("AB205",40, true);
        vehicleDAO.save(bus6);
        boolean isBus1InMaintenance = maintenanceDAO.isVehicleInMaintenance(bus1);
        System.out.println("Il bus " + bus1.getVehicleId() + (isBus1InMaintenance ? " è" : " NON è") + " in manutenzione oggi.");


        System.out.println("\n Manutenzioni attive oggi:");
        List<Maintenance> attiveOggi = maintenanceDAO.findActiveMaintenancesOnDate(LocalDate.now());

        if (attiveOggi.isEmpty()) {
            System.out.println("Nessuna manutenzione attiva.");
        } else {
            for (Maintenance m : attiveOggi) {
                System.out.println("- Veicolo: " + m.getVehicle() +
                        ", dal " + m.getStartDate() + " al " + m.getEndDate() +
                        ", causa: " + m.getMaintenanceCause());
            }
        }


        System.out.println("\n Storico manutenzioni per il bus con id: " + bus1.getVehicleId());
        List<Maintenance> storicoBus1 = maintenanceDAO.findByVehicle(bus1);

        if (storicoBus1.isEmpty()) {
            System.out.println("Nessuna manutenzione trovata per questo veicolo.");
        } else {
            for (Maintenance m : storicoBus1) {
                System.out.println("- Dal " + m.getStartDate() + " al " + m.getEndDate() +
                        " (causa: " + m.getMaintenanceCause() + ")");
            }
        }

        Duration difference = td.calculateTimeDifference(1L);
        System.out.println("Time difference in minutes: " + difference.toMinutes());

        String info = td.getTimeDifferenceInfo(1L);
        System.out.println(info);

        Service servizioBus6 = new Service(
                bus6,
                LocalDate.of(2025, 7, 1),
                LocalDate.of(2025, 7, 10)
        );
        serviceDAO.save(servizioBus6);
        System.out.println("Periodo di servizio aggiunto: " + servizioBus6);






        Maintenance manut = new Maintenance(
                bus6,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                "Controllo freni"
        );
        maintenanceDAO.save(manut);


        boolean inServizioOggi = serviceDAO.isVehicleInService(bus6, LocalDate.now());
        System.out.println("Il bus oggi " + (inServizioOggi ? "È in servizio" : "NON è in servizio"));


        // 1. Creo un bus7
        Bus bus7 = new Bus("CD309", 50, true);
        vehicleDAO.save(bus7);

        // 2. Creo manutenzioni per bus7
        Maintenance manut1 = new Maintenance(
                bus7,
                LocalDate.of(2025, 7, 5),
                LocalDate.of(2025, 7, 8),
                "Controllo freni"
        );
        maintenanceDAO.save(manut1);

        Maintenance manut4 = new Maintenance(
                bus7,
                LocalDate.of(2025, 7, 15),
                LocalDate.of(2025, 7, 18),
                "Revisione motore"
        );
        maintenanceDAO.save(manut4);

        // 3. Recupero e stampo tutte le manutenzioni di bus7
        List<Maintenance> manutenzioniBus7 = maintenanceDAO.findByVehicle(bus7);
        System.out.println("Manutenzioni per bus7:");
        manutenzioniBus7.forEach(System.out::println);

        // 4. Controllo lo stato di bus7 in date diverse
        LocalDate[] datesToCheck = {
                LocalDate.of(2025, 7, 4),  // prima manutenzione
                LocalDate.of(2025, 7, 6),  // durante prima manutenzione
                LocalDate.of(2025, 7, 10), // tra manutenzioni
                LocalDate.of(2025, 7, 16), // durante seconda manutenzione
                LocalDate.of(2025, 7, 20)  // dopo manutenzioni
        };

        for (LocalDate date : datesToCheck) {
            String status = serviceDAO.getVehicleStatus(bus7, date);
            System.out.printf("Data %s: Stato del bus7 = %s%n", date, status);
        }

        // 5. Recupero e stampo tutti i veicoli in servizio e in manutenzione oggi
        LocalDate today1 = LocalDate.now();
        List<Vehicle> inServiceToday = serviceDAO.getVehiclesInService(today1);
        List<Vehicle> inMaintenanceToday = serviceDAO.getVehiclesInMaintenance(today1);

        System.out.println("\nVeicoli in servizio oggi:");
        inServiceToday.forEach(v -> System.out.println(" - Veicolo ID: " + v.getVehicleId()));

        System.out.println("\nVeicoli in manutenzione oggi:");
        inMaintenanceToday.forEach(v -> System.out.println(" - Veicolo ID: " + v.getVehicleId()));


        Service servizioBus7 = new Service(bus7,LocalDate.of(2025,7,1), LocalDate.of(2025,7,10));
        serviceDAO.save(servizioBus7);

        List<Service> serviziBus7 = serviceDAO.findByVehicle(bus7);
        System.out.println("Storico periodi di servizio per il bus7:");
        if (serviziBus7.isEmpty()) {
            System.out.println("Nessun periodo di servizio trovato per questo bus.");
        } else {
            for (Service s : serviziBus7) {
                System.out.printf("- Dal %s al %s%n", s.getStartDate(), s.getEndDate());
            }
        }



        long vidimati = ticketDAO.countValidatedTickets();
        long nonVidimati = ticketDAO.countNonValidatedTickets();

        System.out.println("Ticket vidimati: " + vidimati);
        System.out.println("Ticket NON vidimati: " + nonVidimati);



        Vendor selectedVendor = vd.findById(1L);
        Card selectedCard = cd.findById(3L);

        vd.issueTicket(selectedVendor, 1.50);


        vd.issuePass(selectedVendor, selectedCard, PassType.WEEKLY);

        //test query biglietti emessi in tot giorni
        Vendor vendor = vd.findById(1L);
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        vd.findTicketsIssuedByVendorBetween(vendor, fromDate, toDate);

        em.close();
        emf.close();
    }

}
