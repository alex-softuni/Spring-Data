package entities.FootballBetting;

import jakarta.persistence.*;

@Entity
@Table(name = "player_positions")
public class Position {
    @Id
    @Enumerated(EnumType.STRING)
    private PositionEnum id;

    @Basic
    private String description;

    @ManyToOne
    private Player player;

    public Position() {
    }

    public PositionEnum getId() {
        return id;
    }

    public void setId(PositionEnum id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
