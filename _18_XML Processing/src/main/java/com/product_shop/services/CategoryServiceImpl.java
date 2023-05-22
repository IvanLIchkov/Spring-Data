package com.product_shop.services;

import com.product_shop.entities.categories.ExportCategoriesDto;
import com.product_shop.entities.categories.ExportCategoryByCountDto;
import com.product_shop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public ExportCategoriesDto findAllCategoriesCount(){
        List<ExportCategoryByCountDto> categories = productRepository.categoriesByProductCount();

        ExportCategoriesDto exportCategoriesDto = new ExportCategoriesDto(categories);

        return exportCategoriesDto;
    }

}
