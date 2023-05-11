package com.example.jsonprocessing.productShop.repositories;

import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import com.example.jsonprocessing.productShop.entities.products.Product;
import com.example.jsonprocessing.productShop.entities.products.ProductWithoutBuyerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.jsonprocessing.productShop.entities.products.ProductWithoutBuyerDto(p.name, p.price, p.seller.firstName, p.seller.lastName) " +
            "FROM Product p " +
            "WHERE p.price > :rangeStart and p.price < :rangeEnd and p.buyer IS null " +
            "ORDER BY p.price ASC")
    List<ProductWithoutBuyerDto> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal rangeStart, BigDecimal rangeEnd);

    @Query("select new com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto(c.name,count(p), avg(p.price), sum(p.price)) " +
            "from Product p " +
            "join p.categories c " +
            "group by c " +
            "order by count(p) desc")
    List<CategoryByProductsCountDto> selectAllCategoriesCount();
}
