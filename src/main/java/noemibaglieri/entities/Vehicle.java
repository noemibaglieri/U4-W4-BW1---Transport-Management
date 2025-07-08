package noemibaglieri.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    protected Long vehicleId;


    protected String type;
    protected int size;
    @Column(name = "is_active")
    protected boolean isActive;

    public Vehicle() {}

    public Vehicle( String type, int size, boolean isActive) {
        this.type = type;
        this.size = size;
        this.isActive = isActive;
    }

    @OneToMany(mappedBy = "vehicle")
     private List<VehicleTrip> vehicleTripList;

    @OneToMany(mappedBy = "vehicle")
    private List<Maintenance> maintenanceList;

    @OneToMany(mappedBy = "vehicle")
    private List<Ticket> ticketList;


    public Long getVehicleId() {
        return vehicleId;
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
