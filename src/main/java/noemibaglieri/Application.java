package noemibaglieri;

import noemibaglieri.dao.*;
import noemibaglieri.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import noemibaglieri.exceptions.VendorUnavailableException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

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

        int scelta = -1;
        while (scelta != 0) {
            System.out.println("Digita 1 per selezionare il menu amministratore");
            System.out.println("Digita 2 per selezionare il menu utente");
            System.out.println("Digita 0 per uscire");
            scelta = scanner.nextInt();

            if (scelta == 1) {
                int adminChoice = -1;
                while (adminChoice != 0 && adminChoice != 5) {
                    System.out.println("Inserire la password");
                    int password = scanner.nextInt();
                    if (password == 1234) {
                        adminChoice = -1;
                        while (adminChoice != 0 && adminChoice != 5) {
                            System.out.println("Benvenuto!!!");
                            System.out.println("Digita 1 per Vedere Biglietti o Abbonamenti venduti per ogni punto vendita");
                            System.out.println("Digita 2 per vedere il Parco Mezzi");
                            System.out.println("Digita 3 per vedere i biglietti vidimati");
                            System.out.println("Digita 5 per tornare indietro");
                            System.out.println("Digita 0 per uscire");
                            adminChoice = scanner.nextInt();

                            if (adminChoice == 1) {
                                int ticketChoice = -1;
                                while (ticketChoice != 0 && ticketChoice != 5) {
                                    System.out.println("Digita 1 per vedere i Biglietti");
                                    System.out.println("Digita 2 per vedere gli Abbonamenti");
                                    System.out.println("Digita 5 per tornare indietro");
                                    System.out.println("Digita 0 per uscire");
                                    ticketChoice = scanner.nextInt();

                                    if (ticketChoice == 1) {
                                        scanner.nextLine();
                                        System.out.println("Inserisci l'ID del venditore e il periodo da controllare (Data inizio - Data fine)");
                                        for (Vendor v : vd.findAll()) {
                                            if (v instanceof HumanVendor human) {
                                                System.out.printf("  ID %d: %s (apertura %s - chiusura %s)%n",
                                                        human.getVendorId(),
                                                        human.getVendorName(),
                                                        human.getOpeningTime(),
                                                        human.getClosingTime());
                                            } else if (v instanceof MachineVendor machine) {
                                                System.out.printf("  ID %d: %s  %s)%n",
                                                        machine.getVendorId(),
                                                        machine.getVendorName(),
                                                        machine.isActive() ? "ATTIVA" : "NON ATTIVA");
                                            }
                                        }

                                        System.out.println("Inserisci ID del rivenditore:");
                                        long id = scanner.nextLong();
                                        scanner.nextLine();

                                        Vendor vendorObj = vd.findById(id);
                                        String vendorName = vendorObj.getVendorName();

                                        System.out.println("Data inizio: (AAAA/MM/GG) ");
                                        LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);
                                        System.out.println("Data fine:(AAAA/MM/GG) ");
                                        LocalDate dataFine = LocalDate.parse(scanner.nextLine(), formatter);
                                        System.out.println(vd.findTicketsIssuedByVendorBetween(vendorName, data, dataFine));
                                    } else if (ticketChoice == 2) {
                                        System.out.println("Funzione non implementata");
                                        System.out.println("Digita 1 per riprovare");
                                        System.out.println("Digita 5 per tornare indietro");
                                        System.out.println("Digita 0 per uscire");
                                        int retry = scanner.nextInt();
                                        if (retry == 5) ticketChoice = 5;
                                        if (retry == 0) ticketChoice = 0;
                                    }
                                }
                            } else if (adminChoice == 2) {
                                int vehicleChoice = -1;
                                while (vehicleChoice != 0 && vehicleChoice != 5) {
                                    System.out.println("Benvenuto nel parco Mezzi");
                                    System.out.println("Digita 1 per vedere i veicoli in servizio");
                                    System.out.println("Digita 2 per vedere i veicoli in manutenzione");
                                    System.out.println("Digita 5 per tornare indietro");
                                    System.out.println("Digita 0 per uscire");
                                    vehicleChoice = scanner.nextInt();

                                    if (vehicleChoice == 1) {
                                        List<Service> services = serviceDAO.findAll();
                                        for (Service s : services) {
                                            System.out.printf("ID %d | Veicolo %d | Dal %s al %s%n",
                                                    s.getServiceId(),
                                                    s.getVehicle().getVehicleId(),
                                                    s.getStartDate(),
                                                    s.getEndDate());
                                        }
                                    } else if (vehicleChoice == 2) {
                                        List<Maintenance> allMaint = maintenanceDAO.findAll();
                                        for (Maintenance m : allMaint) {
                                            System.out.printf("  ID %d: veicolo %s, dal %s al %s, motivo: %s%n",
                                                    m.getMaintenanceId(),
                                                    m.getVehicle().getVehicleId(),
                                                    m.getStartDate(),
                                                    m.getEndDate(),
                                                    m.getMaintenanceCause());
                                        }
                                    }
                                }
                            } else if (adminChoice == 3) {
                                System.out.println("Ecco i biglietti vidimati: " + ticketDAO.countValidatedTickets());
                                System.out.println("Ecco i biglietti non ancora vidimati: " + ticketDAO.countNonValidatedTickets());
                            }
                        }
                    } else {
                        System.out.println("Password errata!");
                    }
                }
            } else if (scelta == 2) {
                int userChoice = -1;
                while (userChoice != 0 && userChoice != 5) {
                    System.out.println("Benvenuto nel menù utente");
                    System.out.println("Digita 1 per comprare i biglietti");
                    System.out.println("Digita 2 per fare l'abbonamento");
                    System.out.println("Digita 3 per vidimare un biglietto");
                    System.out.println("Digita 5 per tornare indietro");
                    System.out.println("Digita 0 per uscire");
                    userChoice = scanner.nextInt();

                    if (userChoice == 1) {

                    } else if (userChoice == 2) {
                        System.out.println("Funzionalità abbonamenti.");
                    } else if (userChoice == 3) {
                        List<Ticket> pending = ticketDAO.findPendingTickets();
                        if (pending.isEmpty()) {
                            System.out.println("Nessun biglietto da vidimare.");
                        } else {
                            for (Ticket t : pending) {
                                System.out.printf("ID %d | Prezzo: \u20ac%.2f | Data: %s%n",
                                        t.getTicketId(), t.getPrice(), t.getDateOfPurchase());
                            }

                            System.out.println("Inserisci l'ID del biglietto da vidimare:");
                            long ticketId = scanner.nextLong();
                            Ticket ticket = ticketDAO.findTicketById(ticketId);

                            if (ticket.isValidated()) {
                                System.out.println("Il biglietto \u00e8 gi\u00e0 stato vidimato.");
                            } else {
                                System.out.println("Mezzi disponibili:");
                                List<Vehicle> allVehicles = vehicleDAO.findAll();
                                for (Vehicle v : allVehicles) {
                                    System.out.printf("  ID %d | Tipo: %s | Nome: %s | Attivo: %s%n",
                                            v.getVehicleId(),
                                            v.getClass().getSimpleName(),
                                            v.getName(),
                                            v.isActive() ? "S\u00ec" : "No");
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
            }
        }

        em.close();
        emf.close();
    }
}
