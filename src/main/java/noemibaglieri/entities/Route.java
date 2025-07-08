package noemibaglieri.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="routes")

public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;
   @Column(name="start_area")
    private String startArea;
    @Column(name="end_area")
    private String endArea;
    @Column(name="expected_duration")
    private int expectedDuration;

    @OneToMany(mappedBy = "route")
    private List<VehicleTrip> vehicleTripList;

    public Route() {}

    public Route(String startArea, String endArea, int expectedDuration) {

        this.startArea = startArea;
        this.endArea = endArea;
        this.expectedDuration = expectedDuration;
    }

    public Long getRouteId() {
        return routeId;
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

