package noemibaglieri.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("bus")

public class Bus extends Vehicle {

    public Bus(){}

    public Bus( int size, boolean isActive) {
        super( "bus", size, isActive);

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