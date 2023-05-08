package com.example.springdataautomapping;

import com.example.springdataautomapping.entities.Game;
import com.example.springdataautomapping.entities.User;
import com.example.springdataautomapping.handlers.GameHandler;
import com.example.springdataautomapping.handlers.ShoppingCart;
import com.example.springdataautomapping.handlers.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {

    private final UserHandler userHandler;
    private final GameHandler gameHandler;
    private final ShoppingCart shoppingCart;
    private static User logedUser = null;

    @Autowired
    public CommandRunner(UserHandler userHandler, GameHandler gameHandler, ShoppingCart shoppingCart) {
        this.userHandler = userHandler;
        this.gameHandler = gameHandler;
        this.shoppingCart = shoppingCart;
    }

    @Transactional
    public void addItems(){

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome");
        Scanner sc = new Scanner(System.in);
        String input =sc.nextLine();
        while (!input.equals("Exit")){
            String[] data = input.split("\\|");
            String command = data[0];
            try{
                switch (command) {
                    case "RegisterUser" -> userHandler.registration(data);
                    case "LoginUser" -> logedUser = userHandler.login(data);
                    case "Logout" -> {
                        userHandler.logOut();
                        logedUser = null;

                    }
                    case "AllGames" -> gameHandler.printTitlesAndPrice();
                    case "DetailGame" -> gameHandler.printDetailsAboutGame(data[1]);
                }
                if(logedUser!=null && logedUser.isAdmin()){
                    switch (command) {
                        case "AddGame" -> gameHandler.addGame(data);
                        case "EditGame" -> gameHandler.editGame(data);
                        case "DeleteGame" -> {
                            int id = Integer.parseInt(data[1]);
                            gameHandler.deleteGame(id);
                        }
                        case "AddItem" -> shoppingCart.addItem(data[1],logedUser);
                        case "RemoveItem" -> shoppingCart.removeItem(data[1]);
                        case "BuyItem" -> shoppingCart.buyGames(logedUser);
                    }
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        }
        System.out.println("Nice day");
    }
}
