package com.example.jsonprocessing.productShop.services;

import com.example.jsonprocessing.productShop.entities.products.ProductNameAndPriceDto;
import com.example.jsonprocessing.productShop.entities.products.SoldProductsDto;
import com.example.jsonprocessing.productShop.entities.users.*;
import com.example.jsonprocessing.productShop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDto> getUsersWithSoldProducts() {

        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        return allWithSoldProducts
                .stream()
                .map(user -> this.mapper.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsersWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderedByCount();

        return this.userRepository.findAllWithSoldProducts()
                .stream()
                .map(user -> {
                    final UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto userDto =
                            this.mapper.map(user, UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto.class);
                    userDto.setSoldProducts((SoldProductsDto) user
                            .getSellingItems()
                            .stream()
                            .filter(sale -> sale.getBuyer() != null)
                            .map(sale -> this.mapper.map(sale, SoldProductsDto.class))
                            .collect(Collectors.toSet()));
                    return userDto;
                }).collect(Collectors.toList());

    }

}
