package com.example.football.models.dto;

import com.example.football.models.entity.PlayerPosition;

public class ExportBestPlayerDto {
    private String fullName;
    private PlayerPosition position;
    private String teamName;
    private String stadiumName;


    public ExportBestPlayerDto(String fullName, PlayerPosition position, String teamName, String stadiumName) {
        this.fullName = fullName;
        this.position = position;
        this.teamName = teamName;
        this.stadiumName = stadiumName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Override
    public String toString() {
        return String.format("Player - %s\n" +
                "\tPosition - %s\n" +
                "\tTeam - %s\n" +
                "\tStadium - %s\n",this.fullName,
                this.position,
                this.teamName,
                this.stadiumName);
    }
}
