package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double shooting;

    @Column(nullable = false)
    private double passing;

    @Column(nullable = false)
    private double endurance;

    public Stat() {
    }

    public Stat(Long id, double shooting, double passing, double endurance) {
        this.id = id;
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getPassing() {
        return passing;
    }

    public void setPassing(double passing) {
        this.passing = passing;
    }

    public double getEndurance() {
        return endurance;
    }

    public void setEndurance(double endurance) {
        this.endurance = endurance;
    }
}
