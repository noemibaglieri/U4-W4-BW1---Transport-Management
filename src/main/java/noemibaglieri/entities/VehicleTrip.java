package noemibaglieri.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="trips")
public class VehicleTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    private LocalDate date;

    @Column(name="actual_duration")
    private int actualDuration;

    public VehicleTrip() {}

    public VehicleTrip(Vehicle vehicle, Route route, LocalDate date, int actualDuration) {
        this.vehicle = vehicle;
        this.route = route;
        this.date = date;
        this.actualDuration = actualDuration;
    }

    // Getter e setter...

    public Long getTripId() {
        return tripId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(int actualDuration) {
        this.actualDuration = actualDuration;
    }

    @Override
    public String toString() {
        return "VehicleTrip{" +
                "tripId=" + tripId +
                ", vehicle=" + vehicle +
                ", route=" + route +
                ", date=" + date +
                ", actualDuration=" + actualDuration +
                '}';
    }
}
