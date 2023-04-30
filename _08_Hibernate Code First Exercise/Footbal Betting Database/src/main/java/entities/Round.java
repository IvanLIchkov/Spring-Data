package entities;

import javax.persistence.*;

@Entity
@Table
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Round(){}
}
