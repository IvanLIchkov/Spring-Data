package entities;

import javax.persistence.*;

@Entity
@Table
public class CompetitionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public CompetitionType(){}

}
