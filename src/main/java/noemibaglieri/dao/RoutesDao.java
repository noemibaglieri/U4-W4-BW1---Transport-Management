package noemibaglieri.dao;

import noemibaglieri.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import noemibaglieri.entities.Route;


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

}
