package entities.FootballBetting;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private int squadNumber;
    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "player")
    private Set<Position> positions;

    public Player() {
        this.positions = new HashSet<>();
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

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Position> getPlayerPositions() {
        return positions;
    }

    public void setPlayerPositions(Set<Position> positions) {
        this.positions = positions;
    }
}
