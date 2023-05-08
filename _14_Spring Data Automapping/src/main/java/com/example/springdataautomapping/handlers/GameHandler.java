package com.example.springdataautomapping.handlers;

import com.example.springdataautomapping.dto.DetailGameDto;
import com.example.springdataautomapping.dto.GameDto;
import com.example.springdataautomapping.entities.Game;
import com.example.springdataautomapping.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class GameHandler {

    private final GameService gameService;
    private final static ModelMapper mapper = new ModelMapper();

    @Autowired
    public GameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Transactional
    public void addGame(String[] data){
        String title = data[1];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(data[2]));
        double size = Double.parseDouble(data[3]);
        String trailer = data[4];
        String thubnaiUrl = data[5];
        String description = data[6];
        LocalDate releaseDate = LocalDate.parse(data[7], DateTimeFormatter.ofPattern("d-M-yyyy"));

        Game game = new Game(title,price,size,trailer,thubnaiUrl,description,releaseDate);
        gameService.addGame(game);
    }

    @Transactional
    public void editGame(String[] data) {
        int id = Integer.parseInt(data[1]);
        BigDecimal newPrice = BigDecimal.valueOf(Double.parseDouble(data[2].split("=")[1]));
        double newSize = Double.parseDouble(data[3].split("=")[1]);

        int edited  = gameService.updateGame(id, newPrice, newSize);
        if(edited != 1){
            throw new IllegalArgumentException("No game with given id!");
        }
        System.out.println("Game has been edited!");
    }

    @Transactional
    public void deleteGame(int id) {
        int deleted = gameService.delete(id);
        if(deleted != 1){
            throw new IllegalArgumentException("Invalid id!");
        }
        System.out.println("Game has been deleted!");
    }

    public void printTitlesAndPrice() {
         gameService.selectAllGames()
                .stream()
                .map(g -> mapper.map(g, GameDto.class))
                .forEach(System.out::println);

    }

    public void printDetailsAboutGame(String title) {
        System.out.println(mapper.map(gameService.selectGameByName(title), DetailGameDto.class));
    }
}
