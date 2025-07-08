package noemibaglieri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.VehicleTrip;
import noemibaglieri.exceptions.NotFoundTrip;


public class VehicleTripDao {
    private EntityManager entityManager;
    public VehicleTripDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public  void save (VehicleTrip newVehicleTrip){
        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();

        entityManager.persist(newVehicleTrip);

        transaction.commit();

        System.out.println("You have successfully saved the vehicle trip: " + newVehicleTrip.getTripId());



    }

    public VehicleTrip findById  ( long TripId){
        VehicleTrip found = entityManager.find(VehicleTrip.class,TripId);
        if (found == null) throw new NotFoundTrip(TripId);
        return found;
    }
}
