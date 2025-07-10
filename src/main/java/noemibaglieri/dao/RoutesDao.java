package noemibaglieri.dao;

import jakarta.persistence.TypedQuery;
import noemibaglieri.entities.VehicleTrip;
import noemibaglieri.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Route;

import java.util.List;


public class RoutesDao {

    private EntityManager entityManager;

    public RoutesDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save (Route newRoute){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(newRoute);

        transaction.commit();
        System.out.println("The route " + newRoute.getRouteId() + " was successfully created in db");
    }

    public Route findById (long routeId){
        Route found = entityManager.find(Route.class,routeId);
        if (found == null) throw new NotFoundException(routeId);
        return found;
    }

    //in base alla route ritorno la lista di mezzi che hano percorso la rotta
    public List<VehicleTrip> findVehiclesByRoute(long routeId) {
        try {
            Route route = findById(routeId);

            TypedQuery<VehicleTrip> query = entityManager.createQuery(
                    "SELECT vt FROM VehicleTrip vt WHERE vt.route.routeId = :routeId",
                    VehicleTrip.class
            );
            query.setParameter("routeId", routeId);
            List<VehicleTrip> trips = query.getResultList();

            if (trips.isEmpty()) {
                System.out.println("No vehicles found for route " + routeId +
                        " (" + route.getStartArea() + " to " + route.getEndArea() + ")");
            }

            return trips;

        } catch (NotFoundException e) {
            throw new NotFoundException(routeId);
        } catch (Exception e) {
            throw new RuntimeException("Error while searching for vehicles on route " + routeId + ": " + e.getMessage());
        }
    }

    public List<Route> findAll() {
        TypedQuery<Route> q = entityManager.createQuery(
                "SELECT r FROM Route r", Route.class
        );
        return q.getResultList();
    }

}
