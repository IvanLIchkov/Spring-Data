package com.example._12_springdataadvancedqueringexercise.models;

import com.example._12_springdataadvancedqueringexercise.Enums.AgeRestriction;
import com.example._12_springdataadvancedqueringexercise.Enums.EditionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "edition_type")
    @Enumerated(EnumType.ORDINAL)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private Long copies;

    @Column(name = "releasa_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction")
    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToMany
    private Set<Category> categorySet;


    public Book() {
        this.categorySet = new HashSet<>();
    }

    public Book(String title, EditionType editionType, BigDecimal price, AgeRestriction ageRestriction) {
        this();
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.ageRestriction = ageRestriction;
    }

    public Book(String title,
                EditionType editionType,
                BigDecimal price,
                Long copies,
                LocalDate releaseDate,
                AgeRestriction ageRestriction,
                Author author,
                Set<Category> categorySet) {
        this();
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categorySet = categorySet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }


}
