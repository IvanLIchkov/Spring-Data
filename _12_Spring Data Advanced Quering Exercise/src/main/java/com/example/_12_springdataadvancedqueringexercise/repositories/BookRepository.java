package com.example._12_springdataadvancedqueringexercise.repositories;

import com.example._12_springdataadvancedqueringexercise.Enums.AgeRestriction;
import com.example._12_springdataadvancedqueringexercise.Enums.EditionType;
import com.example._12_springdataadvancedqueringexercise.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String fName, String lName);


    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int lessThanCopies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(double lower, double higher);

    @Query("select b from Book b where year(b.releaseDate) != year(:releaseDate) ")
    List<Book> findAllByReleaseDateNotInYear(@Param("releaseDate") LocalDate releaseDate);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContains(String string);

    List<Book> findByAuthorLastNameStartingWith(String string);

    @Query("select count(b) from Book b where length(b.title) > :length")
    int countBooksByTitle(@Param("length") int length);

    @Query("select new Book (b.title, b.editionType,b.price, b.ageRestriction) from Book b where b.title = :titleName")
    Book selectBookByGivenTitle(@Param("titleName") String title);

    @Modifying
    @Query(value = "update Book b set b.copies = b.copies + :copies where b.releaseDate > :date ")
    int updateCopies(@Param(value = "date") LocalDate date,@Param(value = "copies") long copies);

    @Modifying
    @Query("DELETE Book b where b.copies < :copies")
    void removeBooksWithLowerCopies(@Param("copies") long copies);
}
