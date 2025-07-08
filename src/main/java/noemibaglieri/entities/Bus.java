package noemibaglieri.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity


public class Bus extends Vehicle {

    public Bus(){}

    public Bus(int vehicleId, int size, boolean isActive) {
        super(vehicleId, "bus", size, isActive);

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