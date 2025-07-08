package noemibaglieri.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="routes")

public class Route {
    private int routeId;
    private String startArea;
    private String endArea;
    private int expectedDuration;

    @OneToMany
    private List<VehicleTrip> vehicleTripList;

    public Route() {}

    public Route(int routeId, String startArea, String endArea, int expectedDuration) {
        this.routeId = routeId;
        this.startArea = startArea;
        this.endArea = endArea;
        this.expectedDuration = expectedDuration;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }


}

