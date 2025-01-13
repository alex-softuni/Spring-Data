package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "Bike")
public class Bike extends Vehicle {
    private final static String type = "Bike";
    public Bike() {
        super(type);
    }

}
