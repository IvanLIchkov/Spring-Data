package com.example.jsonprocessing.productShop.entities.users;

import com.example.jsonprocessing.productShop.entities.products.SoldProductsDto;

import java.io.Serializable;

public class UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto implements Serializable {

    private String firstname;

    private int age;

    private SoldProductsDto soldProducts;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
