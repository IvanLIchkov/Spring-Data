package com.example.jsonprocessing.productShop.services;


import com.example.jsonprocessing.productShop.entities.users.UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto;
import com.example.jsonprocessing.productShop.entities.users.UserWithSoldProductsDto;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDto> getUsersWithSoldProducts();

    List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsersWithSoldProductsOrderByCount();
}
