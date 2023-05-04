package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate year1990);

}
