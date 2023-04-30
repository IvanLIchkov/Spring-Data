package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@IdClass(BetGame.class)
public class BetGame implements Serializable {


    @Id
    private Long gameId = getGameId();

    @Id
    private double bet;

    @OneToOne
    private Game game;

    @ManyToOne
    @JoinColumn
    private ResultPrediction resultPrediction;

    public BetGame(){}

    private Long getGameId(){
        return this.game.getId();
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
