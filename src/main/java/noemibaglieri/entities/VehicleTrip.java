package noemibaglieri.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="trips")

public class VehicleTrip {
    private int tripId;
    private Vehicle vehicle;
    private Route route;
    private LocalDate date;
    private int actualDuration;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicles;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route routes;

    public VehicleTrip(){}

    public VehicleTrip(int tripId, Vehicle vehicle, Route route, LocalDate date, int actualDuration) {
        this.tripId = tripId;
        this.vehicle = vehicle;
        this.route = route;
        this.date = date;
        this.actualDuration = actualDuration;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
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
