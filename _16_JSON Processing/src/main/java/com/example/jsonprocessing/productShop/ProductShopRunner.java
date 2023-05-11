package com.example.jsonprocessing.productShop;

import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import com.example.jsonprocessing.productShop.entities.products.ProductWithoutBuyerDto;
import com.example.jsonprocessing.productShop.entities.users.UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto;
import com.example.jsonprocessing.productShop.entities.users.UserWithSoldProductsDto;
import com.example.jsonprocessing.productShop.services.CategoryService;
import com.example.jsonprocessing.productShop.services.ProductService;
import com.example.jsonprocessing.productShop.services.SeedService;
import com.example.jsonprocessing.productShop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final Gson gson;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        printJsonProductsForSale();
//        printUsersWithSoldProducts();

//        printAllProdcutsByCategoryCount();

        List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> usersWithSoldProductsOrderByCount = this.userService.getUsersWithSoldProductsOrderByCount();

        System.out.println();
    }

    private void printAllProdcutsByCategoryCount() {
        List<CategoryByProductsCountDto> categoriesByCount = this.productService.getCategoriesByCount();

        String json = gson.toJson(categoriesByCount);
        System.out.println(json);
    }

    private void printUsersWithSoldProducts() {
        List<UserWithSoldProductsDto> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = this.gson.toJson(usersWithSoldProducts);
        System.out.println(json);
    }

    private void printJsonProductsForSale() {
        List<ProductWithoutBuyerDto> productsForSell = this.productService.getProductsInPriceRangeForSell(500, 1000);

        String json = this.gson.toJson(productsForSell);

        System.out.println(json);
    }
}
