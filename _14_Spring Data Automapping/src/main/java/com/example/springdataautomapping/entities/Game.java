package com.example.springdataautomapping.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(min = 3, max = 100)
    private String title;

    @Column
    @Min(value = 0)
    private BigDecimal price;

    @Column
    @Min(value = 0)
    private double size;

    @Column
    private String trailer;

    @Column
    private String imageThumbnail;

    @Column()
    @Size(min = 20)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, BigDecimal price, double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this();
        setTitle(title);
        setPrice(price);
        this.size = size;
        setTrailer(trailer);
        setImageThumbnail(imageThumbnail);
        setDescription(description);
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length()<3 || title.length()>100){
            throw new IllegalArgumentException("Title must have length between 3 and 100 symbols!");
        }
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.valueOf(0))<0){
            throw new IllegalArgumentException("Price must be positive number");
        }
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        if(size<0){
            throw new IllegalArgumentException("Size must be positive number");
        }
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        if(trailer.length()!=11){
            throw new IllegalArgumentException("Illegal trailer url!");
        }
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        Pattern httpPattern = Pattern.compile("^https?:\\/\\/(?!.*:\\/\\/)\\S+");
        Matcher matcher = httpPattern.matcher(imageThumbnail);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Illegal thumb Url");
        }
        this.imageThumbnail = imageThumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.length()<20){
            throw new IllegalArgumentException("Description must be at least 20 symbols");
        }
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
