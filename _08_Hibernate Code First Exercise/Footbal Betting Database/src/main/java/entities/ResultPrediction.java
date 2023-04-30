package entities;

import javax.persistence.*;

@Entity
@Table
public class ResultPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String prediction;

    public ResultPrediction(){}
}
