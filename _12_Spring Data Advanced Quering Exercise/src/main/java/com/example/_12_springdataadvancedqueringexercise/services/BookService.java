package com.example._12_springdataadvancedqueringexercise.services;


import com.example._12_springdataadvancedqueringexercise.Enums.AgeRestriction;
import com.example._12_springdataadvancedqueringexercise.Enums.EditionType;
import com.example._12_springdataadvancedqueringexercise.models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService  {

    List<Book> selectBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> selectByEditionTypeAndCopies(EditionType editionType, int lessThanCopies);

    List<Book> selectBookByPriceRange(double lower, double higher);

    List<Book> selectBooksByReleaseDateBefore(LocalDate releaseDate);

    List<Book> selectAllBooksThatContains(String string);

    List<Book> selectAllWhichNameStartsWith(String string);

    int selectBooksCountWithTitleLongerThan(int length);

   Book selectBookByTitle(String title);

    long increaseBooksCopies(LocalDate date, int increaseWith);

    void removeBooksWithLowerCopiesThan(long copies);
}
