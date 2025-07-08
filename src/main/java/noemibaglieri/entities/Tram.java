package noemibaglieri.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("tram")

public class Tram extends Vehicle {

    public Tram(){}


    public Tram( int size, boolean isActive) {
        super( "tram", size, isActive);

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", size=" + size +
                ", isActive=" + isActive +

                '}';
    }


}
