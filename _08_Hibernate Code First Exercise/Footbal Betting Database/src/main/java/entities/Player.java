package entities;

import javax.persistence.*;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private short squadNumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;

    @Column(name = "is_currently_injured")
    private boolean isCurrentlyInjured;

    public Player(){}
}
