package com.example.jsonprocessing.productShop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductsCountDto {

    private String category;

    @XmlElement(name = "product-count")
    private long productsCount;

    @XmlElement(name = "averagePrice")
    private double averagePrice;

    @XmlElement(name = "totalRevenue")
    private BigDecimal totalRevenue;

    public CategoryByProductsCountDto() {
    }

    public CategoryByProductsCountDto(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
