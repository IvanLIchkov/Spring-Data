package com.example.jsonprocessing.productShop.entities.categories;

import entities.Department;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCategoryStatsDto implements Serializable {


    @XmlElement(name = "name")
    private String category;

    @XmlElement(name = "product-count")
    private long productsCount;

    @XmlElement(name = "averagePrice")
    private double averagePrice;

    @XmlElement(name = "totalRevenue")
    private BigDecimal totalRevenue;

    public XmlCategoryStatsDto() {
    }

    public XmlCategoryStatsDto(CategoryByProductsCountDto other) {
        this();
        this.category = other.getCategory();
        this.productsCount = other.getProductsCount();
        this.averagePrice = other.getAveragePrice();
        this.totalRevenue = other.getTotalRevenue();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "XmlCategoryStatsDto{" +
                "category='" + category + '\'' +
                ", productsCount=" + productsCount +
                ", averagePrice=" + averagePrice +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
