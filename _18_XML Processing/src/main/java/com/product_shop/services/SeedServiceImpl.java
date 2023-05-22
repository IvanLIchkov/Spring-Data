package com.product_shop.services;

import com.product_shop.entities.categories.Category;
import com.product_shop.entities.categories.CategoryImportDTO;
import com.product_shop.entities.products.Product;
import com.product_shop.entities.products.ProductsImportDto;
import com.product_shop.entities.users.User;
import com.product_shop.entities.users.UsersImportDto;
import com.product_shop.repositories.CategoryRepository;
import com.product_shop.repositories.ProductRepository;
import com.product_shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class    SeedServiceImpl implements SeedService {

    private static final String PRODUCTS_XML_PATH = "/Users/scopi/Desktop/Spring-Data/_18_XML Processing/src/main/resources/productShopXMLResources/products.xml";
    private static FileReader fileReader;
    private static final String USERS_XML_PATH = "/Users/scopi/Desktop/Spring-Data/_18_XML Processing/src/main/resources/productShopXMLResources/users.xml";
    private static final String CATEGORIES_XML_PATH = "/Users/scopi/Desktop/Spring-Data/_18_XML Processing/src/main/resources/productShopXMLResources/categories.xml";

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();
    }


    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        UsersImportDto usersDtos = (UsersImportDto) unmarshaller.unmarshal(new FileReader(USERS_XML_PATH));

        List<User> users = usersDtos.getUsers()
                .stream()
                .map(dto -> this.mapper.map(dto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);

    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        CategoryImportDTO importDto = (CategoryImportDTO) unmarshaller.unmarshal(new FileReader(CATEGORIES_XML_PATH));

        List<Category> entities = importDto.getCategories()
                .stream()
                .map(cn -> new Category(cn.getName()))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(entities);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        ProductsImportDto importDto = (ProductsImportDto) unmarshaller.unmarshal(new FileReader(PRODUCTS_XML_PATH));

       List<Product> products = importDto.getProducts()
               .stream()
               .map(p -> this.mapper.map(p, Product.class))
               .map(this::setRandomBuyer)
               .map(this::setRandomSeller)
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
