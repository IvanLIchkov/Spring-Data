package com.example.jsonprocessing.productShop.services;

import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import com.example.jsonprocessing.productShop.entities.products.ProductWithoutBuyerDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductWithoutBuyerDto> getProductsInPriceRangeForSell(float from, float to);

    List<CategoryByProductsCountDto> getCategoriesByCount();
}
