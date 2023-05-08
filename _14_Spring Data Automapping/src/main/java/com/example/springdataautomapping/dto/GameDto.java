package com.example.springdataautomapping.dto;

import java.math.BigDecimal;

public class GameDto {
    private String title;
    private BigDecimal price;

    public GameDto() {
    }

    public GameDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f",this.title, this.price);
    }
}
