package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Route;
import noemibaglieri.entities.VehicleTrip;
import noemibaglieri.exceptions.InvalidTripTimesException;
import noemibaglieri.exceptions.NotFoundTrip;

import java.time.Duration;
import java.time.LocalDateTime;


public class VehicleTripDao {
    private EntityManager entityManager;

    public VehicleTripDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(VehicleTrip newVehicleTrip) {
        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();

        entityManager.persist(newVehicleTrip);

        transaction.commit();

        System.out.println("You have successfully saved the vehicle trip: " + newVehicleTrip.getTripId());


    }

    public VehicleTrip findById(long TripId) {
        VehicleTrip found = entityManager.find(VehicleTrip.class, TripId);
        if (found == null) throw new NotFoundTrip(TripId);
        return found;
    }

    //metodo per calcolare la differenza di durata dei viaggi

    public Duration calculateTimeDifference(long tripId) {
        VehicleTrip trip = findById(tripId);
        Route route = trip.getRoute();
        int estimatedMinutes = route.getExpectedDuration();
        int actualMinutes = trip.getActualDuration();
        int differenceInMinutes = actualMinutes - estimatedMinutes;

        return Duration.ofMinutes(differenceInMinutes);

    }

    //metodo per stampare la differenza tra due viaggi (ci è voluto di meno o di più)

    public String getTimeDifferenceInfo(long tripId) {
        Duration diff = calculateTimeDifference(tripId);
        long minutes = diff.toMinutes();
        if (minutes > 0) { return "The trip was" + minutes + "minutes longer than expected";
        } else if (minutes < 0 ) { return "The trip was" + minutes + "minutes shorter than expected";
        } else { return "The trip duration matched the expected duration";}
    }

}











