package com.example.jsonprocessing.productShop.services;


import com.product_shop.entities.users.UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto;
import com.product_shop.entities.users.UserWithSoldProductsDto;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDto> getUsersWithSoldProducts();

    List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsersWithSoldProductsOrderByCount();
}
