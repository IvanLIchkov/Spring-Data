package entities;

import javax.persistence.*;

@Entity
@Table
public class Position {
    @Id
    @Column(length = 2)
    private String id;

    @Column(nullable = false)
    private String description;

    public Position(){}
}
