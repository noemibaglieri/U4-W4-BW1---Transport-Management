package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.HumanVendor;
import noemibaglieri.entities.MachineVendor;
import noemibaglieri.entities.Ticket;
import noemibaglieri.entities.Vendor;
import noemibaglieri.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

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


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Ticket ticket = new Ticket(LocalDate.now(), price, vendor);
        entityManager.persist(ticket);

        transaction.commit();
        System.out.println(" Ticket issued by vendor '" + vendor.getVendorName() + "' with ID: " + ticket.getTicketId() + " and price: €" + price);
    }


}
