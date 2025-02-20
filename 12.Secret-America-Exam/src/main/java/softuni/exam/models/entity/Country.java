package softuni.exam.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private double area;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
