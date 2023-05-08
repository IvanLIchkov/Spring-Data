package com.example.springdataautomapping.handlers;

import com.example.springdataautomapping.entities.Game;
import com.example.springdataautomapping.entities.User;
import com.example.springdataautomapping.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCart {

   private static final List<Game> cart = new ArrayList<>();
   private final GameRepository gameRepository;

   @Autowired
   public ShoppingCart(GameRepository gameRepository) {
      this.gameRepository = gameRepository;
   }


   public void addItem(String title, User user) {
      List<Game> games = user.getGames();
      Game game = gameRepository.findGameByTitle(title);
      if(game == null){
         throw new IllegalArgumentException("No such game in shop.");
      }
//      if(games.contains(game)){
//         throw new IllegalArgumentException(title +" already owned!");
//      }
      if (cart.contains(game)) {
         throw new IllegalArgumentException(title + " is already in the cart!");
      }
      cart.add(game);
      System.out.println(title + " added to cart.");

   }

   public void removeItem(String title) {
      Game game = cart.stream()
              .filter(g -> g.getTitle().equals(title))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException("No such game in cart!"));
      cart.remove(game);
      System.out.println(title + " removed from cart.");
   }

   @Transactional
   public void buyGames(User logedUser) {
      cart.forEach(logedUser::addGame);

   }

   public void clearCart(){
      cart.clear();
   }
}
