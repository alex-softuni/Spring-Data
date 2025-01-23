package entities.FootballBetting.geo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Enumerated(EnumType.STRING)
    private CountryISO id;

    @Basic(optional = false)
    private String name;

    @ManyToMany(mappedBy = "countries")
    private Set<Continent> continents;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public Country() {
        this.towns = new HashSet<Town>();
        this.continents = new HashSet<>();
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public CountryISO getId() {
        return id;
    }

    public void setId(CountryISO id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
