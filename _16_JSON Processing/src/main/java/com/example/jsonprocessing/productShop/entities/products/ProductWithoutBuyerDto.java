package com.example.jsonprocessing.productShop.entities.products;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class ProductWithoutBuyerDto {

    private String name;
    private BigDecimal price;
    private String seller;

    public ProductWithoutBuyerDto(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;

        if(firstName == null){
            this.seller = lastName;
        }else {
            this.seller = firstName+ " "+ lastName;
        }

        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }
}