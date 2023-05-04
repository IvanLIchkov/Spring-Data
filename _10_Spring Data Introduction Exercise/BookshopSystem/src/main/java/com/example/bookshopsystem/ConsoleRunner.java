package com.example.bookshopsystem;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.services.SeedService;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedBooks();

//        this.booksAfter2000();

//        this.allAuthorsWithBooksBefore1990();

//        this.allAuthorsWithBookCount();

//        this.getAllFromAuthor();
    }

    private void getAllFromAuthor() {
        List<Book> books = bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell");

        books.forEach(b -> System.out.printf("%s, %s -> %d.%n",b.getTitle(), b.getReleaseDate(),b.getCopies()));
    }


    private void allAuthorsWithBookCount() {
        List<Author> authors = authorRepository.findAll();

        authors.stream()
                        .sorted((l,r) -> r.getBooks().size() - l.getBooks().size())
                                .forEach(a-> System.out.printf("%s %s -> %d%n",a.getFirstName(),a.getLastName(),a.getBooks().size()));

    }

    private void allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990,1,1);

        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a-> System.out.println(a.getFirstName()+ " " + a.getLastName()));
    }


    private void booksAfter2000(){
        LocalDate year2000 = LocalDate.of(2000,1,1);

        List<Book> books = bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b-> System.out.println(b.getTitle()));
    }
}
