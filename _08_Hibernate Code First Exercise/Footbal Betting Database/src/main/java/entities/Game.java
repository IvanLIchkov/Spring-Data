package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private Team homeTeam;

    @OneToOne
    @JoinColumn
    private Team awayTeam;

    @Column
    private Short homeGoals;

    @Column
    private Short awayGoals;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

    @Column
    private double homeTeamWinBetRate;

    @Column
    private double awayTeamWinBetRate;

    @Column
    private double drawBetRate;

    @ManyToOne
    @JoinColumn
    private Round round;

    @ManyToOne
    @JoinColumn
    private Competition competition;

    public Game(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Short getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Short homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Short getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Short awayGoals) {
        this.awayGoals = awayGoals;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public double getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public double getDrawBetRate() {
        return drawBetRate;
    }

    public void setDrawBetRate(double drawBetRate) {
        this.drawBetRate = drawBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
