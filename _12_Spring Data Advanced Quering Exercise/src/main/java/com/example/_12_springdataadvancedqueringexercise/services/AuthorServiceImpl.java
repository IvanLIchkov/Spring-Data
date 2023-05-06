package com.example._12_springdataadvancedqueringexercise.services;

import com.example._12_springdataadvancedqueringexercise.models.Author;
import com.example._12_springdataadvancedqueringexercise.models.AuthorNamesWithTotalCount;
import com.example._12_springdataadvancedqueringexercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        long randomIndex = new Random().nextLong(size)+1;

        return authorRepository.findById(randomIndex).orElseThrow();
    }

    public List<Author> selectAuthorWhichNameEndsWithGivenLetter(String letterToEndWith) {
        return authorRepository.findAllByFirstNameEndingWith(letterToEndWith);
    }

    @Override
    public List<AuthorNamesWithTotalCount>getCountOfCopies() {
        return authorRepository.findCountOfCopies();
    }

    @Override
    public int getBookCountByGivenAuthor(String firstName, String lastName) {
        return authorRepository.udp_get_count_of_books_by_author(firstName,lastName);
    }


}
