package com.example.springdataautomapping.services;

import com.example.springdataautomapping.dto.GameDto;
import com.example.springdataautomapping.entities.Game;
import com.example.springdataautomapping.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;


    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGame(Game game) {
        gameRepository.save(game);
    }


    @Override
    public int updateGame(int id, BigDecimal newPrice, double newSize) {
        return gameRepository.updateGame(id,newPrice,newSize);
    }

    @Override
    public int delete(int id) {
        return gameRepository.deleteGameByIdEquals(id);
    }

    @Override
    public List<Game> selectAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game selectGameByName(String title) {
        return gameRepository.findGameByTitle(title);
    }
}
