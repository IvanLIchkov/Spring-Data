package com.example.jsonprocessing.productShop.services;

import com.example.jsonprocessing.productShop.entities.categories.CategoriesImportDto;
import com.example.jsonprocessing.productShop.entities.categories.Category;
import com.example.jsonprocessing.productShop.entities.products.Product;
import com.example.jsonprocessing.productShop.entities.products.ProductImportDto;
import com.example.jsonprocessing.productShop.entities.users.User;
import com.example.jsonprocessing.productShop.entities.users.UserImportDto;
import com.example.jsonprocessing.productShop.repositories.CategoryRepository;
import com.example.jsonprocessing.productShop.repositories.ProductRepository;
import com.example.jsonprocessing.productShop.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class    SeedServiceImpl implements SeedService {

    private static final String PRODUCTS_JSON_PATH = "/Users/scopi/Desktop/Spring-Data/_16_JSON Processing/src/main/resources/recourcesForProductShop/products.json";
    private static FileReader fileReader;
    private static final String USERS_JSON_PATH = "/Users/scopi/Desktop/Spring-Data/_16_JSON Processing/src/main/resources/recourcesForProductShop/users.json";
    private static final String CATEGORIES_JSON_PATH = "/Users/scopi/Desktop/Spring-Data/_16_JSON Processing/src/main/resources/recourcesForProductShop/categories.json";
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }


    @Override
    public void seedUsers() throws FileNotFoundException {
        fileReader = new FileReader(USERS_JSON_PATH);
        UserImportDto[] userImportDtos = this.gson.fromJson(fileReader, UserImportDto[].class);

        List<User> users = Arrays.stream(userImportDtos)
                .map(importDto -> this.mapper.map(importDto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
       fileReader = new FileReader(CATEGORIES_JSON_PATH);
        CategoriesImportDto[] categoriesImportDtos = this.gson.fromJson(fileReader,CategoriesImportDto[].class);

        List<Category> categories = Arrays.stream(categoriesImportDtos)
                .map(categoryDto ->mapper.map(categoryDto, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        fileReader = new FileReader(PRODUCTS_JSON_PATH);
        ProductImportDto[] productImportDtos = this.gson.fromJson(fileReader, ProductImportDto[].class);

        List<Product> products = Arrays.stream(productImportDtos)
                .map(productDto -> this.mapper.map(productDto, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .collect(Collectors.toList());

        this.productRepository.saveAll(products);

    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDbCount) + 1;
            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }
        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(944))>0){
            return product;
        }
        product.setBuyer(getRandomUser());
        return product;

    }

    private Product setRandomSeller(Product product){
        product.setSeller(getRandomUser());

        return product;
    }

    private User getRandomUser() {
        long usersCount = this.userRepository.count();

        int randomUSerId = new Random().nextInt((int) usersCount)+1;

        return this.userRepository.findById(randomUSerId).get();
    }
}
