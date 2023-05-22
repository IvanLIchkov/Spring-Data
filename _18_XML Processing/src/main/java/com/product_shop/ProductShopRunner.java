package com.product_shop;

import com.product_shop.entities.categories.ExportCategoriesDto;
import com.product_shop.entities.products.ExportProductsInRangeDto;
import com.product_shop.entities.users.userSoldProductsDto.ExportSellersDto;
import com.product_shop.services.CategoryService;
import com.product_shop.services.ProductService;
import com.product_shop.services.SeedService;
import com.product_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedAll();
//        productsInRange();
//        soldProducts();
//        categoriesByCount();
        this.userService.getAllUsersWithSoldProduct();
    }

    private void categoriesByCount() throws JAXBException {
        ExportCategoriesDto allCategoriesCount = this.categoryService.findAllCategoriesCount();

        JAXBContext context = JAXBContext.newInstance(ExportCategoriesDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(allCategoriesCount, System.out);
    }

    private void soldProducts() throws JAXBException {
        ExportSellersDto result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(result, System.out);
    }

    private void productsInRange() throws JAXBException {
        ExportProductsInRangeDto inRange = this.productService.getInRange(500, 1000);
        JAXBContext context = JAXBContext.newInstance(ExportProductsInRangeDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(inRange, System.out);
    }
}

