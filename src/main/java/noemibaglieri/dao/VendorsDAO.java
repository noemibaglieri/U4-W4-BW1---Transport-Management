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
        System.out.println("The vendor * " + newVendor.getVendorName() + " * was successfully registered.");
    }

    public Vendor findById(Long vendorId) {
        Vendor found = entityManager.find(Vendor.class, vendorId);
        if (found == null) throw new UserNotFoundException(vendorId);
        return found;

    }
     //metodo per vedere se un venditore è attivo

    private boolean isVendorAvailable(Vendor vendor) {
        if (vendor instanceof MachineVendor machine) {
            return machine.isActive();
        } else if (vendor instanceof HumanVendor human) {
            LocalTime now = LocalTime.now();
            return !now.isBefore(human.getOpeningTime()) && !now.isAfter(human.getClosingTime());
        }
        return false;
    }

//metodo per emettere un ticket

public void issueTicket(Vendor vendor, double price) {
    if (!isVendorAvailable(vendor)) {
        throw new VendorUnavailableException(vendor.getVendorName());
    }
    
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    Ticket ticket = new Ticket(LocalDate.now(), price, vendor);
    entityManager.persist(ticket);

    transaction.commit();
    System.out.println("Ticket issued by vendor '" + vendor.getVendorName()
            + "' with ID: " + ticket.getTicketId() + " and price: €" + price);
}

//metodo per emettere un pass
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
    System.out.println( type + " pass issued by vendor '" + vendor.getVendorName()
            + "' for card ID: " + card.getId() + " (Pass ID: " + pass.getPassId() + ")");
}

//query per cercare un numero di ticket emessi da un vendor in un lasso di tempo
public List<Ticket> findTicketsIssuedByVendorBetween(Vendor vendor, LocalDate fromDate, LocalDate toDate) {
    if (vendor == null) {
        throw new VendorNotFoundException(null); // oppure passare un ID se lo conosci
    }
    TypedQuery<Ticket> query = entityManager.createQuery(
            "SELECT t FROM Ticket t WHERE t.vendor = :vendor AND t.dateOfPurchase BETWEEN :from AND :to", Ticket.class);
    query.setParameter("vendor", vendor);
    query.setParameter("from", fromDate);
    query.setParameter("to", toDate);

    List<Ticket> result = query.getResultList();

    System.out.println("Found " + result.size() + " tickets issued by vendor '" + vendor.getVendorName() +
            "' between " + fromDate + " and " + toDate);

    return result;
}




























}
