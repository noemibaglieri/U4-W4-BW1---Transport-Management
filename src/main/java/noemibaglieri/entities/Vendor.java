package noemibaglieri.entities;

import jakarta.persistence.*;

@Entity
@Table(name="vendors")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_id")
    protected Long vendorId;

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
