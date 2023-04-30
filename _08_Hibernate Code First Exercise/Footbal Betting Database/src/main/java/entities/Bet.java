package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bet_money")
    private double betMoney;

    @Column(name = "time_of_bet")
    private LocalDateTime timeOfBet;

    @ManyToOne
    private User user;

    public Bet(){}
}
