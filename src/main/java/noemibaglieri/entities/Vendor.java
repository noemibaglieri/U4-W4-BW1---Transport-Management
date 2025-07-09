package noemibaglieri.entities;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.ListAttribute;

import java.util.List;

@Entity
@Table(name="vendors")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "vendor_type")
public abstract class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_id")
    protected Long vendorId;
    protected String name;

    @OneToMany(mappedBy = "vendor")
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "vendor")
    private List<Pass> passList;

    public Vendor() {}

    public Vendor(String name) {
        this.name = name;
    }

    public Long getVendorId() {
        return vendorId;
    }


    public String getVendorName() {
        return name;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                '}';
    }
}
