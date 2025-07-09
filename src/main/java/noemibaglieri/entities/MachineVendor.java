package noemibaglieri.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="machines_vendors")
@DiscriminatorValue("Machine")
public class MachineVendor extends Vendor {

    @Column(name="is_active")
    private boolean isActive;

    public MachineVendor() {
    }

    public MachineVendor(String name, boolean isActive) {
        super(name);
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override

    public String toString() {
        return "MachineVendor{" +
                "shopName=" + name +
                "isActive=" + isActive +
                "vendorId=" + vendorId +
                '}';
    }
}
