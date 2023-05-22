package com.product_shop.services;


import com.product_shop.entities.users.UsersAndProductsDto.UsersDto;
import com.product_shop.entities.users.userSoldProductsDto.ExportSellersDto;

public interface UserService {

    ExportSellersDto findAllWithSoldProducts();

    UsersDto getAllUsersWithSoldProduct();
}
