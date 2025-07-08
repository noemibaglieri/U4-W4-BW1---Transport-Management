package noemibaglieri.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    protected Long maintenanceId;
@Column(name="vehicle_type")
    private Vehicle vehicleType;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="maintenance_cause")
    private String maintenanceCause;


    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;


    private Vehicle vehicles;
    public Maintenance(){}

    public Maintenance( Vehicle vehicleType, LocalDate startDate, LocalDate endDate, String maintenanceCause) {

        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maintenanceCause = maintenanceCause;
    }

    public Vehicle getVehicles() {
        return vehicles;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getMaintenanceCause() {
        return maintenanceCause;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Vehicle getVehicleType() {
        return vehicleType;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setVehicleType(Vehicle vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMaintenanceCause(String maintenanceCause) {
        this.maintenanceCause = maintenanceCause;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setVehicles(Vehicle vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceId=" + maintenanceId +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", maintenanceCause='" + maintenanceCause + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
