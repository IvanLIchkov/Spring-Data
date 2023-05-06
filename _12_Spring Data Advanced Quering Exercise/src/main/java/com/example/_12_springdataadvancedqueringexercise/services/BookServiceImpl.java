package com.example._12_springdataadvancedqueringexercise.services;

import com.example._12_springdataadvancedqueringexercise.Enums.AgeRestriction;
import com.example._12_springdataadvancedqueringexercise.Enums.EditionType;
import com.example._12_springdataadvancedqueringexercise.models.Book;
import com.example._12_springdataadvancedqueringexercise.repositories.AuthorRepository;
import com.example._12_springdataadvancedqueringexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> selectBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> selectByEditionTypeAndCopies(EditionType editionType, int lessThanCopies) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, lessThanCopies);
    }

    @Override
    public List<Book> selectBookByPriceRange(double lower, double higher) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lower,higher);
    }

    @Override
    public List<Book> selectBooksByReleaseDateBefore(LocalDate releaseDate) {
            return this.bookRepository.findAllByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> selectAllBooksThatContains(String string) {
        return bookRepository.findAllByTitleContains(string);
    }

    @Override
    public List<Book> selectAllWhichNameStartsWith(String string) {
        return bookRepository.findByAuthorLastNameStartingWith(string);
    }

    @Override
    public int selectBooksCountWithTitleLongerThan(int length) {
        return bookRepository.countBooksByTitle(length);
    }

    @Override
    public Book selectBookByTitle(String title) {
        return bookRepository.selectBookByGivenTitle(title);
    }

    @Override
    public long increaseBooksCopies(LocalDate date, int increaseWith) {
        return bookRepository.updateCopies(date,increaseWith);
    }

    @Override
    public void removeBooksWithLowerCopiesThan(long copies) {
        bookRepository.removeBooksWithLowerCopies(copies);
    }


}
