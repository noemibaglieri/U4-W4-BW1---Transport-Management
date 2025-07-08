package noemibaglieri.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="vehicles")

public abstract class Vehicle {
    protected int vehicleId;
    protected String type;
    protected int size;
    protected boolean isActive;

    public Vehicle() {}

    public Vehicle(int vehicleId, String type, int size, boolean isActive) {
        this.vehicleId = vehicleId;
        this.type = type;
        this.size = size;
        this.isActive = isActive;
    }

    @OneToMany
     private List<VehicleTrip> vehicleTripList;

    @OneToMany
    private List<Maintenance> maintenanceList;

    @OneToMany
    private List<Ticket> ticketList;


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", isActive=" + isActive +
                '}';
    }
}
