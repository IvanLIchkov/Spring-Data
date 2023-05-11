package com.example.jsonprocessing.productShop.services;

import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import com.example.jsonprocessing.productShop.entities.products.ProductWithoutBuyerDto;
import com.example.jsonprocessing.productShop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDto> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(rangeStart, rangeEnd);
    }

    @Override
    public List<CategoryByProductsCountDto> getCategoriesByCount() {
        return this.productRepository.selectAllCategoriesCount();
    }
}
