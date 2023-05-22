package com.product_shop.services;

import com.product_shop.entities.products.ExportProductInRangeDto;
import com.product_shop.entities.products.Product;
import com.product_shop.entities.products.ExportProductsInRangeDto;
import com.product_shop.entities.users.User;
import com.product_shop.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ExportProductInRangeDto> productToDtoMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();

//        Converter<User, String> userToFullNameConverter =
//                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ExportProductInRangeDto> typeMap =
                this.mapper.createTypeMap(Product.class, ExportProductInRangeDto.class);

        this.productToDtoMapping = typeMap.addMappings(map
                -> map.map(Product::getFullName, ExportProductInRangeDto::setName));
    }

    @Override
    public ExportProductsInRangeDto getInRange(int start, int end) {
        BigDecimal rangeFrom = BigDecimal.valueOf(start);
        BigDecimal rangeTo = BigDecimal.valueOf(end);

        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(rangeFrom, rangeTo);

        List<ExportProductInRangeDto> dtos =
                products
                        .stream()
                        .map(this.productToDtoMapping::map)
                        .collect(Collectors.toList());

        return new ExportProductsInRangeDto(dtos);
    }
}
