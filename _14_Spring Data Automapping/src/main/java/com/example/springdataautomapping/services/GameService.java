package com.example.springdataautomapping.services;

import com.example.springdataautomapping.dto.GameDto;
import com.example.springdataautomapping.entities.Game;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(Game game);

    int updateGame(int id, BigDecimal newPrice, double newSize);

    int delete(int id);

    List<Game> selectAllGames();

    Game selectGameByName(String title);
}
