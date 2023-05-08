package com.example.springdataautomapping.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetailGameDto extends GameDto {

    private String description;
    private LocalDate releaseDate;

    public DetailGameDto() {
        super();
    }

    public DetailGameDto(String title, BigDecimal price, String description, LocalDate releaseDate) {
        super(title, price);
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s",this.getTitle(), this.getPrice(), this.description, this.releaseDate);
    }
}
