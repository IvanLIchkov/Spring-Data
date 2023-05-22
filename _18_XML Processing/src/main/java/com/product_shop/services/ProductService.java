package com.product_shop.services;

import com.product_shop.entities.products.ExportProductsInRangeDto;

public interface ProductService {

    ExportProductsInRangeDto getInRange(int start, int end);
}
