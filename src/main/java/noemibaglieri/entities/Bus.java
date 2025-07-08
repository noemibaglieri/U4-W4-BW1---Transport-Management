package noemibaglieri.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("bus")

public class Bus extends Vehicle {

    public Bus(){}

    public Bus(String name, int size, boolean isActive) {
        super(name, size, isActive);

    }



    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleIdBus=" + vehicleId +
                ", size=" + size +
                ", isActive=" + isActive +

                '}';
    }
}