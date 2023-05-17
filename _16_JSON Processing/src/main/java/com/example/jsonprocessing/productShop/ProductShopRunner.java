package com.example.jsonprocessing.productShop;

import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import com.example.jsonprocessing.productShop.entities.categories.XMLCategoryStatsList;
import com.example.jsonprocessing.productShop.entities.categories.XmlCategoryStatsDto;
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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

//        List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> usersWithSoldProductsOrderByCount = this.userService.getUsersWithSoldProductsOrderByCount();
//
//        System.out.println();

//        xmlCategoryByProductsCountIntoXmlFormat();
        fromXmlDemo();
    }

    private void fromXmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                " <category>\n" +
                "            <name>Sports</name>\n" +
                "            <product-count>81</product-count>\n" +
                "            <averagePrice>804.60321</averagePrice>\n" +
                "            <totalRevenue>65172.86</totalRevenue>\n"+
                "       </category>";
        JAXBContext context = JAXBContext.newInstance(XmlCategoryStatsDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XmlCategoryStatsDto result = (XmlCategoryStatsDto) unmarshaller.unmarshal(inputStream);

        System.out.println(result);
    }

    private void xmlCategoryByProductsCountIntoXmlFormat() throws JAXBException {
        List<CategoryByProductsCountDto> result = this.productService.getCategoriesByCount();

        List<XmlCategoryStatsDto> xmlResult=
                result
                        .stream()
                        .map(XmlCategoryStatsDto::new)
                        .collect(Collectors.toList());

        XMLCategoryStatsList xmlCategoryStatsList = new XMLCategoryStatsList(xmlResult);
        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlCategoryStatsList, System.out);
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
