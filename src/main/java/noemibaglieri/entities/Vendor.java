package noemibaglieri.entities;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.ListAttribute;

import java.util.List;

@Entity
@Table(name="vendors")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_id")
    protected Long vendorId;

    @OneToMany
    private List<Ticket> ticketList;
    @OneToMany
    private List<Pass> passList;

    public Vendor() {}

    public Long getVendorId() {
        return vendorId;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                '}';
    }
}
