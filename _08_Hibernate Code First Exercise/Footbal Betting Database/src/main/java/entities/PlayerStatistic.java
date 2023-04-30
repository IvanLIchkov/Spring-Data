package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@IdClass(PlayerStatistic.class)
public class PlayerStatistic implements Serializable  {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "scored_goals")
    private short scoredGoals;

    @Column(name = "assists")
    private short playerAssists;

    @Column(name = "minutes_played")
    private short minutesPlayed;
    public PlayerStatistic(){}
}
