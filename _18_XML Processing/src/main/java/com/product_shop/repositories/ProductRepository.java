package com.product_shop.repositories;

import com.product_shop.entities.categories.Category;
import com.product_shop.entities.categories.ExportCategoryByCountDto;
import com.product_shop.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal rangeTo, BigDecimal rangeTo1);

    @Query("select new com.product_shop.entities.categories.ExportCategoryByCountDto(c.name, count(p), avg(p.price), sum(p.price)) " +
            "from Product p " +
            "join p.categories c " +
            "group by c " +
            "order by count(p) desc ")
    List<ExportCategoryByCountDto> categoriesByProductCount();
}
