package noemibaglieri.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="maintenance")
public class Maintenance {
    private int maintenanceId;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String maintenanceCause;

    @ManyToOne
    @JoinColumn(name="vehicle_id")


    private Vehicle vehicles;
    public Maintenance(){}

    public Maintenance(int maintenanceId, Vehicle vehicle, LocalDate startDate, LocalDate endDate, String maintenanceCause) {
        this.maintenanceId = maintenanceId;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maintenanceCause = maintenanceCause;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMaintenanceCause() {
        return maintenanceCause;
    }

    public void setMaintenanceCause(String maintenanceCause) {
        this.maintenanceCause = maintenanceCause;
    }
}
