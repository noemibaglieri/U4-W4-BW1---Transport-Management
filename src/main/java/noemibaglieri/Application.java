package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.enums.PassType;
import noemibaglieri.exceptions.InvalidPassException;
import noemibaglieri.exceptions.PassNotFoundException;
import noemibaglieri.exceptions.VendorUnavailableException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W4BW1");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);
        ServiceDAO serviceDAO = new ServiceDAO(em);
        RoutesDao routesDao = new RoutesDao(em);
        VendorsDAO vd = new VendorsDAO(em);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        System.out.println("Digita 1 per selezionare il menu amministratore");
        System.out.println("Digita 2 per selezionare il menu utente");
        int Scelta = scanner.nextInt();

        if (Scelta == 1){
            System.out.println("Inserire la password");
            Scelta = scanner.nextInt();
            if (Scelta == 1234){
                System.out.println("Benvenuto!!!");
                System.out.println("Digita 1 per Vedere Biglietti o Abbonamenti venduti per ogni punto venidta");
                System.out.println("Digita 2 per vedere il Parco Mezzi");
                System.out.println("Digita 3 per vedere i biglietti vidimati");
                System.out.println("Digita 4 per vidimare un biglietto");
                System.out.println("Digita 0 per uscire");
                Scelta = scanner.nextInt();
                if (Scelta == 1){
                    System.out.println("Digita 1 per vedere i Biglietti");
                    System.out.println("Digita 2 per vedere gli Abbonamenti");

                    Scelta = scanner.nextInt();
                    if (Scelta == 1){
                        scanner.nextLine();
                        System.out.println("Inserisci l'ID del venditore e il periodo da controllare (Data inizio - Data fine)");
                        for (Vendor v : vd.findAll()) {
                            if (v instanceof HumanVendor human) {
                                System.out.printf("  ID %d: %s (apertura %s - chiusura %s)%n",
                                        human.getVendorId(),
                                        human.getVendorName(),
                                        human.getOpeningTime(),
                                        human.getClosingTime()
                                );
                            } else if (v instanceof MachineVendor machine) {
                                System.out.printf("  ID %d: %s  %s)%n",
                                        machine.getVendorId(),
                                        machine.getVendorName(),
                                        machine.isActive() ? "ATTIVA" : "NON ATTIVA"
                                );
                            }
                        }

                        System.out.println("Inserisci ID del rivenditore:");
                        long id = scanner.nextLong();
                        scanner.nextLine();

                        Vendor vendorObj = vd.findById(id);
                        String vendorName = vendorObj.getVendorName();


                        System.out.println();
                        System.out.println("Data inizio: (AAAA/MM/GG) ");
                        String dataString = scanner.nextLine();
                        LocalDate data = LocalDate.parse(dataString, formatter);
                        System.out.println("Data fine:(AAAA/MM/GG) ");
                        String dataStringFine = scanner.nextLine();
                        LocalDate dataFine = LocalDate.parse(dataStringFine, formatter);
                        System.out.println(vd.findTicketsIssuedByVendorBetween(vendorName,data, dataFine));
                    }
                }else if (Scelta == 2) {
                    System.out.println("Benvenuto nel parco Mezzi \n digita 1 per vedere i veicoli in servizio \n digita 2 per vedere i veicoli in manutenzione");
                    Scelta = scanner.nextInt();
                    if (Scelta == 1){
                        System.out.println("Ecco i mezzi in Servizio");
                    } else if (Scelta == 2) {
                        System.out.println("Digita 1 per vedere i mezzi in manutenzione in una determinata data");
                        System.out.println("Digita 2 per vedere i mezzi in manutenzione tramite ID");
                        System.out.println("Premi 3 per vedere se un mezzo è in manutenzione");
                        System.out.println("Premi 4 per vedere tutte le manutenzioni");

                        Scelta = scanner.nextInt();

                        if (Scelta == 1){
                            scanner.nextLine();
                            System.out.println("Data inizio: (AAAA/MM/GG) ");
                            String dataString = scanner.next();

                            LocalDate data = LocalDate.parse(dataString, formatter);
                            System.out.println(maintenanceDAO.findActiveMaintenancesOnDate(data));
                        } else if (Scelta == 2) {
                            System.out.println("Seleziona l'ID");
                            long id = scanner.nextLong();
                            System.out.println(maintenanceDAO.find(id));
                        }else if (Scelta == 3){
                            System.out.println("Digita l'ID del mezzo");
                            long vehicleId = scanner.nextLong();
                            Vehicle vehicle = vehicleDAO.find(vehicleId);
                            if (vehicle == null) {
                                System.out.println("Veicolo con ID " + vehicleId + " non trovato.");
                            } else {
                                boolean inManutenzione = maintenanceDAO.isVehicleInMaintenance(vehicle);
                                if (inManutenzione) {
                                    System.out.println("Il veicolo è attualmente in manutenzione.");
                                }else {
                                    System.out.println("Il veicolo " +  vehicleId +  " NON è in manutenzione.");
                                }
                            }
                        }else if (Scelta == 4){
                            System.out.println("Tutte le manutenzioni");
                            List<Maintenance> allMaint = maintenanceDAO.findAll();
                            for (Maintenance m : allMaint) {
                                System.out.printf("  ID %d: veicolo %s, dal %s al %s, motivo: %s%n",
                                        m.getMaintenanceId(),
                                        m.getVehicle().getVehicleId(),
                                        m.getStartDate(),
                                        m.getEndDate(),
                                        m.getMaintenanceCause()
                                );
                            }
                        }

                    }
                }else if(Scelta == 3){
                    System.out.println("Ecco i biglietti vidimati");
                    System.out.println(ticketDAO.countValidatedTickets());
                    System.out.println("Ecco i biglietti non ancora vidimati");
                    System.out.println(ticketDAO.countNonValidatedTickets());
                }else if (Scelta == 4) {
                    List<Ticket> pending = ticketDAO.findPendingTickets();
                    if (pending.isEmpty()) {
                        System.out.println("Nessun biglietto da vidimare.");
                    } else {
                        System.out.println("Biglietti non ancora vidimati:");
                        for (Ticket t : pending) {
                            System.out.printf("ID %d | Prezzo: €%.2f | Data: %s%n",
                                    t.getTicketId(), t.getPrice(), t.getDateOfPurchase());
                        }

                        System.out.println("Inserisci l'ID del biglietto da vidimare:");
                        long ticketId = scanner.nextLong();
                        Ticket ticket = ticketDAO.findTicketById(ticketId);


                        if (ticket.isValidated()) {
                            System.out.println("Il biglietto è già stato vidimato.");
                        } else {
                            System.out.println("Mezzi disponibili:");
                            List<Vehicle> allVehicles = vehicleDAO.findAll();
                            for (Vehicle v : allVehicles) {
                                System.out.printf("  ID %d | Tipo: %s | Nome: %s | Attivo: %s%n",
                                        v.getVehicleId(),
                                        v.getClass().getSimpleName(),
                                        v.getName(),
                                        v.isActive() ? "Sì" : "No"
                                );
                            }

                            System.out.println("Inserisci l'ID del mezzo su cui stai vidimando:");
                            long vehicleId = scanner.nextLong();
                            Vehicle vehicle = vehicleDAO.find(vehicleId);

                            if (vehicle == null) {
                                System.out.println("Veicolo non trovato.");
                            } else {
                                ticket.setVehicle(vehicle);
                                ticket.setValidated(true);
                                ticketDAO.update(ticket);
                                System.out.println("Biglietto vidimato con successo su veicolo ID " + vehicleId);
                            }
                        }
                    }
                }


            }
        }else if (Scelta == 2) {
            System.out.println("Benvenuto nel menù utente \n Digita 1 per comprare i biglietti \n Digita 2 per fare l'abbonamento");
            Scelta = scanner.nextInt();
            if (Scelta == 1){
                System.out.println("Dove vuoi acquistare i biglietti?\nDigita 1 per Tabacchino\nDigita 2 per Vending Machine");
                Scelta = scanner.nextInt();

                final double STANDARD_PRICE = 2.80;

                if (Scelta == 1){
                    System.out.println("Tabacchini disponibili:");
                    for (Vendor v : vd.findAll()) {
                        if (v instanceof HumanVendor) {
                            System.out.printf("  ID %d: %s (apertura %s - chiusura %s)%n",
                                    v.getVendorId(),
                                    v.getVendorName(),
                                    ((HumanVendor)v).getOpeningTime(),
                                    ((HumanVendor)v).getClosingTime()
                            );
                        }
                    }

                    System.out.println("Inserisci l'ID del venditore ");
                    long vendorID = scanner.nextLong();
                    Vendor vendor = vd.findById(vendorID);

                    System.out.println("Inserisci ID della tratta:");

                    System.out.println("Tratte disponibili:");
                    List<Route> allRoutes = routesDao.findAll();
                    for (Route r : allRoutes) {
                        System.out.println(r);
                    }
                    long routeId = scanner.nextLong();
                    Route route = routesDao.findById(routeId);
                    if (route == null) {
                        System.out.println("Tratta con ID " + routeId + " non trovata. Esco.");
                        return;
                    }
                    System.out.println("Hai scelto la tratta: "
                            + route.getStartArea() + " → " + route.getEndArea());

                    System.out.println("Prezzo del biglietto: €" + STANDARD_PRICE);
                    System.out.println("Quanti biglietti vuoi acquistare?");
                    int nBiglietti = scanner.nextInt();

                    for (int i = 0; i < nBiglietti; i++) {
                        try {
                            vd.issueTicket(vendor, STANDARD_PRICE);
                        } catch (VendorUnavailableException vue) {
                            System.out.println("Venditore non disponibile: " + vue.getMessage());
                            break;
                        }
                    }
                    System.out.println("Hai acquistato " + nBiglietti + " biglietti per la tratta indicata.");

                } else if (Scelta == 2) {
                    System.out.println("Vending machine disponibili:");
                    for (Vendor v : vd.findAll()) {
                        if (v instanceof MachineVendor) {
                            MachineVendor m = (MachineVendor) v;
                            System.out.printf("  ID %d: %s (%s)%n",
                                    m.getVendorId(),
                                    m.getVendorName(),
                                    m.isActive() ? "ATTIVA" : "NON ATTIVA"
                            );
                        }
                    }
                    System.out.println("Inserisci l'ID della Vending Machine ");
                    long vendorID = scanner.nextLong();
                    Vendor vendor = vd.findById(vendorID);

                    System.out.println("Inserisci ID della tratta:");

                    System.out.println("Tratte disponibili:");
                    List<Route> allRoutes = routesDao.findAll();
                    for (Route r : allRoutes) {
                        System.out.println(r);
                    }
                    long routeId = scanner.nextLong();
                    Route route = routesDao.findById(routeId);
                    if (route == null) {
                        System.out.println("Tratta con ID " + routeId + " non trovata. Esco.");
                        return;
                    }
                    System.out.println("Hai scelto la tratta: "
                            + route.getStartArea() + " → " + route.getEndArea());

                    System.out.println("Prezzo del biglietto: €" + STANDARD_PRICE);
                    System.out.println("Quanti biglietti vuoi acquistare?");
                    int nBiglietti = scanner.nextInt();

                    for (int i = 0; i < nBiglietti; i++) {
                        try {
                            vd.issueTicket(vendor, STANDARD_PRICE);
                        } catch (VendorUnavailableException vue) {
                            System.out.println("Venditore non disponibile: " + vue.getMessage());
                            break;
                        }
                    }
                    System.out.println("Hai acquistato " + nBiglietti + " biglietti per la tratta indicata.");
                }
            }else if (Scelta == 2) {

                System.out.println("NIENTE DA VEDERE SCEGLI 1 !!!!!!");
            }
        }


        em.close();
        emf.close();
    }

}
