package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import noemibaglieri.entities.*;
import noemibaglieri.enums.PassType;
import noemibaglieri.exceptions.InvalidCardException;
import noemibaglieri.exceptions.UserNotFoundException;
import noemibaglieri.exceptions.VendorNotFoundException;
import noemibaglieri.exceptions.VendorUnavailableException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class VendorsDAO {
    private final EntityManager entityManager;

    public VendorsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Vendor newVendor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newVendor);
        transaction.commit();
        System.out.println("Il venditore * " + newVendor.getVendorName() + " * è stato registrato con successo.");
    }

    public Vendor findById(Long vendorId) {
        Vendor found = entityManager.find(Vendor.class, vendorId);
        if (found == null) throw new UserNotFoundException(vendorId);
        return found;
    }

    private boolean isVendorAvailable(Vendor vendor) {
        if (vendor instanceof MachineVendor machine) {
            return machine.isActive();
        } else if (vendor instanceof HumanVendor human) {
            LocalTime now = LocalTime.now();
            return !now.isBefore(human.getOpeningTime()) && !now.isAfter(human.getClosingTime());
        }
        return false;
    }

    public void issueTicket(Vendor vendor, double price) {
        if (!isVendorAvailable(vendor)) {
            throw new VendorUnavailableException(vendor.getVendorName());
        }

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Ticket ticket = new Ticket(LocalDate.now(), price, vendor);
        entityManager.persist(ticket);

        transaction.commit();
        System.out.println("Biglietto emesso dal venditore '" + vendor.getVendorName()
                + "' con ID: " + ticket.getTicketId() + " e prezzo: €" + price);
    }

    public void issuePass(Vendor vendor, Card card, PassType type) {
        if (!isVendorAvailable(vendor)) {
            throw new VendorUnavailableException(vendor.getVendorName());
        }

        boolean cardValid = card.getExpiryDate().isAfter(LocalDate.now()) && card.isActive();
        if (!cardValid) {
            throw new InvalidCardException(card.getId());
        }

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        LocalDate startDate = LocalDate.now();
        Pass pass = new Pass(card, startDate, type);
        pass.setVendor(vendor);
        entityManager.persist(pass);

        transaction.commit();
        System.out.println("Abbonamento di tipo " + type + " emesso dal venditore '" + vendor.getVendorName()
                + "' per la tessera ID: " + card.getId() + " (ID Abbonamento: " + pass.getPassId() + ")");
    }

    public List<Ticket> findTicketsIssuedByVendorBetween(String vendorName, LocalDate fromDate, LocalDate toDate) {
        if (vendorName == null) {
            throw new VendorNotFoundException(null);
        }

        TypedQuery<Vendor> vendorQuery = entityManager.createQuery(
                "SELECT v FROM Vendor v WHERE v.name = :name", Vendor.class);
        vendorQuery.setParameter("name", vendorName);

        List<Vendor> vendors = vendorQuery.getResultList();
        if (vendors.isEmpty()) {
            throw new VendorNotFoundException(null);
        }

        Vendor vendor = vendors.get(0);

        TypedQuery<Ticket> query = entityManager.createQuery(
                "SELECT t FROM Ticket t WHERE t.vendor = :vendor AND t.dateOfPurchase BETWEEN :from AND :to", Ticket.class);
        query.setParameter("vendor", vendor);
        query.setParameter("from", fromDate);
        query.setParameter("to", toDate);

        List<Ticket> result = query.getResultList();

        System.out.println("Trovati " + result.size() + " biglietti emessi dal venditore '" + vendor.getVendorName() +
                "' tra il " + fromDate + " e il " + toDate);

        return result;
    }

    public List<Vendor> findAll() {
        TypedQuery<Vendor> q = entityManager.createQuery(
                "SELECT v FROM Vendor v", Vendor.class
        );
        return q.getResultList();
    }
}























