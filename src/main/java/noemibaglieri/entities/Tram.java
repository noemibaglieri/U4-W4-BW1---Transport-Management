package noemibaglieri.entities;

import jakarta.persistence.Entity;

@Entity


public class Tram extends Vehicle {

    public Tram(){}


    public Tram( int size, boolean isActive) {
        super( "tram", size, isActive);

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", isActive=" + isActive +

                '}';
    }


}
