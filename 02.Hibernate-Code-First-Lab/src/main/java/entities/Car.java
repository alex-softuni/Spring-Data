package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Car extends Vehicle {
    private final static String type = "Car";
    private int seats;


    public Car() {
    }
    public Car(int seats) {
        super(type);
        this.seats = seats;
    }


    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
