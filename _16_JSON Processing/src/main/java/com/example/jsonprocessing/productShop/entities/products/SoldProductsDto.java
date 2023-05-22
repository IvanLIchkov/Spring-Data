package com.example.jsonprocessing.productShop.entities.products;

import java.util.HashSet;
import java.util.Set;

public class SoldProductsDto {
    private int count;
    private Set<ProductImportDto> soldProducts;

    public SoldProductsDto() {
        this.soldProducts = new HashSet<>();
    }
}
