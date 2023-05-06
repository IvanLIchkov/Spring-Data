package com.example._12_springdataadvancedqueringexercise;

import com.example._12_springdataadvancedqueringexercise.models.AuthorNamesWithTotalCount;
import com.example._12_springdataadvancedqueringexercise.models.Book;
import com.example._12_springdataadvancedqueringexercise.services.AuthorServiceImpl;
import com.example._12_springdataadvancedqueringexercise.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {

    private final BookServiceImpl bookServiceImpl;
    private  final AuthorServiceImpl authorService;

    @Autowired
    public CommandRunner(BookServiceImpl bookServiceImpl, AuthorServiceImpl authorService) {
        this.bookServiceImpl = bookServiceImpl;
        this.authorService = authorService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        //TODO task 1
//        String restriction = sc.nextLine().toUpperCase();
//        AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction);
//
//        this.bookServiceImpl.selectBooksByAgeRestriction(ageRestriction)
//                .forEach(b-> System.out.println(b.getTitle()));

        //TODO task 2
//        this.bookServiceImpl.selectByEditionTypeAndCopies(EditionType.GOLD, 5000)
//                .forEach(b-> System.out.println(b.getTitle()));

        //TODO task 3
//        this.bookServiceImpl.selectBookByPriceRange(5, 40)
//                .forEach(b-> System.out.printf("%s - $%.2f%n",b.getTitle(),b.getPrice()));

        //TODO task 4
//        this.bookServiceImpl.selectBookNotInYear(LocalDate.of(1998,1,1))
//                .forEach(b-> System.out.println(b.getTitle()));

        //TODO task 5
//        LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("d-M-yyyy"));
//        this.bookServiceImpl.selectBooksByReleaseDateBefore(date)
//                .forEach(b-> System.out.printf("%s %s %.2f%n",b.getTitle(),b.getEditionType(),b.getPrice()));

        //TODO task 6
//        String letterToEndWith = sc.nextLine();
//        this.authorService.selectAuthorWhichNameEndsWithGivenLetter(letterToEndWith)
//                .forEach(a-> System.out.println(a.getFirstName()+" "+ a.getLastName()));

        //TODO task 7
//        String string = sc.nextLine();
//        this.bookServiceImpl.selectAllBooksThatContains(string)
//                .forEach(b-> System.out.println(b.getTitle()));

        //TODO task 8
//        String string = sc.nextLine();
//        this.bookServiceImpl.selectAllWhichNameStartsWith(string)
//                .forEach(b -> System.out.printf("%s (%s %s)%n",b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()));

        //TODO task 9
//        int length = sc.nextInt();
//
//        System.out.println(this.bookServiceImpl.selectBooksCountWithTitleLongerThan(length));

        //TODO task 10

//         authorService.getCountOfCopies()
//                 .forEach(s -> System.out.printf("%s %s - %d%n",s.getFirstName(),s.getLastName(),s.getTotalCopies()));

        //TODO task 11
//        String title = sc.nextLine(); //Things Fall Apart
//        Book book = bookServiceImpl.selectBookByTitle(title);
//        System.out.printf("%s %s %s %.2f%n",book.getTitle(), book.getEditionType(), book.getAgeRestriction(),book.getPrice());

        //TODO task 12
//        String stringDate = sc.nextLine();
//        int increaseWith = sc.nextInt();
//        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("d MMM yyyy"));
//        long booksUpdated = bookServiceImpl.increaseBooksCopies(date, increaseWith);
//        System.out.printf("%d books are released after %s, so total of %d book copies were added%n",booksUpdated,stringDate,booksUpdated*increaseWith);

        //TODO task 13
//        long copies = Long.parseLong(sc.nextLine());
//
//        bookServiceImpl.removeBooksWithLowerCopiesThan(copies);

        //TODO task 14
        String[] data = sc.nextLine().split(" ");
        String firstName = data[0];
        String lastName = data[1];

        int bookCountByGivenAuthor = authorService.getBookCountByGivenAuthor(firstName, lastName);

        System.out.printf("%s %s has written %d books%n",firstName,lastName, bookCountByGivenAuthor);

    }
}
