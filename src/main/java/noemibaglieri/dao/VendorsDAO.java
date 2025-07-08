package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Vendor;
import noemibaglieri.exceptions.UserNotFoundException;

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
}
