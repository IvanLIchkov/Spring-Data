package com.example.jsonprocessing.productShop.services;

import com.product_shop.entities.categories.CategoryByProductsCountDto;
import com.product_shop.entities.products.ProductWithoutBuyerDto;

import java.util.List;

public interface ProductService {
    List<ProductWithoutBuyerDto> getProductsInPriceRangeForSell(float from, float to);

    List<CategoryByProductsCountDto> getCategoriesByCount();
}
