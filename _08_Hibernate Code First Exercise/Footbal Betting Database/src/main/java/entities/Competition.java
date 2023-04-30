package entities;

import javax.persistence.*;

@Entity
@Table
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private CompetitionType competitionType;

    public Competition(){}
}
