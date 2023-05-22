package com.example.jsonprocessing.productShop.entities.users;

import com.product_shop.entities.products.SoldProductDto;

import java.util.List;

public class UserWithSoldProductsDto {
    private String firstName;
    private String lastName;
    private List<SoldProductDto> itemsBought;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDto> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<SoldProductDto> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
