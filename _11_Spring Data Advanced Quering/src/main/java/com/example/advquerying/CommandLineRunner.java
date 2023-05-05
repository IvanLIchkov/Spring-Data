package com.example.advquerying;

import com.example.advquerying.repositories.IngredientsRepository;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final ShampooRepository shampooRepository;
    private final IngredientsRepository ingredientsRepository;


    @Autowired
    public CommandLineRunner(ShampooRepository shampooRepository, IngredientsRepository ingredientsRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientsRepository = ingredientsRepository;
    }


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //TODO task 0
        shampooRepository.findByBrand("Cotton Fresh")
                .forEach(s->System.out.printf("%d%n",s.getId()));
//         //TODO task 1
//        shampooRepository.findAllByBrandAndSize("Cotton Fresh", Size.SMALL)
//                .forEach(s->System.out.printf("%d%n",s.getId()));
        Scanner sc = new Scanner(System.in);

        //TODO task 2
//        String sizeName = sc.nextLine();
//        Size size = Size.valueOf(sizeName);
//        Long id = Long.parseLong(sc.nextLine());

//        this.shampooRepository.findBySizeOrderById(size)
//                .forEach(s-> System.out.printf("%s %s %d%n",s.getBrand(),s.getSize(),s.getId()));

//        this.shampooRepository.findBySizeOrLabel_IdOrderByPrice(size,id)
//                .forEach(s-> System.out.printf("%s %s %.2flv.%n",s.getBrand(),s.getSize(),s.getPrice()));


        //TODO task 3
//        BigDecimal price = new BigDecimal(sc.nextLine());

//        this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price)
//                .forEach(s-> System.out.printf("%s %s %.2flv.%n",s.getBrand(),s.getSize(),s.getPrice()));


//          //TODO task 4
//        String letter = sc.nextLine();
//
//        this.ingredientsRepository.findByNameIsStartingWith(letter)
//                .forEach(i -> System.out.println(i.getName()));

//        BigDecimal price = new BigDecimal(sc.nextLine());
//
//        System.out.println(this.shampooRepository.findByPriceIsLessThan(price).size());

        //TODO task 5
//        List<String> names = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            names.add(sc.nextLine());
//        }
//        this.ingredientsRepository.findByNameIn(names)
//                .forEach(i-> System.out.println(i.getName()));

            //TODO task 7
//        Set<String> ingridients = new HashSet<>();
//
//        for (int i = 0; i < 2; i++) {
//            String input = sc.nextLine();
//            ingridients.add(input);
//        }
//        this.shampooRepository.findByIngredientsName(ingridients)
//                .forEach(s-> System.out.println(s.getBrand()));

        //TODO task 8
//        int count = Integer.parseInt(sc.nextLine());
//        this.shampooRepository.getShampooByIngCount(count)
//                .forEach(s-> System.out.println(s.getBrand()));

        //TODO task 9 modifying in Spring need to be @Modifying

//        this.ingredientsRepository.save(new Ingredient("Apple", BigDecimal.valueOf(0.90)));
//
//        String name = sc.nextLine();
//
//       this.ingredientsRepository.removeIngredientByName(name);

        //TODO task 10

//        this.ingredientsRepository.updateIngredientsPrice();

        //TODO task 11

        List<String> names = new ArrayList<>(Arrays.asList("Nettle", "Macadamia Oil", "Aloe Vera"));
        this.ingredientsRepository.updateIngredientsByGivenNames(names);
    }
}
