package entities.FootballBetting.geo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "continent_countries",
            joinColumns = @JoinColumn(name = "countries_id"),
            inverseJoinColumns = @JoinColumn(name = "continents_id")
    )
    private Set<Country> countries;

    @Basic(optional = false)
    private String name;

    public Continent() {
        this.countries = new HashSet<>();
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
