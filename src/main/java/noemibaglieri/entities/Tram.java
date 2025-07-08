package noemibaglieri.entities;

import jakarta.persistence.Entity;

@Entity


public class Tram extends Vehicle {

    public Tram(){}


    public Tram(int vehicleId, int size, boolean isActive) {
        super(vehicleId, "tram", size, isActive);

    }


}
