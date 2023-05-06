package com.example._12_springdataadvancedqueringexercise.repositories;

import com.example._12_springdataadvancedqueringexercise.models.Author;
import com.example._12_springdataadvancedqueringexercise.models.AuthorNamesWithTotalCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate year1990);

    List<Author> findAllByFirstNameEndingWith(String letter);

    @Query("SELECT a.firstName as firstName, " +
            " a.lastName as lastName , " +
            " sum(b.copies) as totalCopies " +
            "from Author a " +
            "join a.books as b " +
            "group by b.author " +
            "order by totalCopies desc ")
    List<AuthorNamesWithTotalCount> findCountOfCopies();

    @Procedure
    int udp_get_count_of_books_by_author(String firstName, String lastName);
}
