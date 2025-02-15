package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Plane extends Vehicle {
    private final static String type = "Plane";
    private int passengerCapacity;
    public Plane() {}
    public Plane(int passengerCapacity) {
        super(type);
        this.passengerCapacity = passengerCapacity;

    }



    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
