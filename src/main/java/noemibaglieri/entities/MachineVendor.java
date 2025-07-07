package noemibaglieri.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="machines_vendors")

public class MachineVendor extends Vendor {

    @Column(name="is_active")
    private boolean isActive;

    public MachineVendor() {
    }

    public MachineVendor(boolean isActive) {
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
                "isActive=" + isActive +
                "vendorId=" + vendorId +
                '}';
    }
}
