package com.example.jsonprocessing.productShop.entities.products;

import java.math.BigDecimal;

public class ProductImportDto {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
