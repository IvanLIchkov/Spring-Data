package com.example.cardealer.entities.part;

import java.math.BigDecimal;

public class PartNameAndPriceDto {
    private String name;
    private BigDecimal price;

    public PartNameAndPriceDto() {
    }


    public PartNameAndPriceDto(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
