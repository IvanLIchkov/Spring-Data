package com.example._12_springdataadvancedqueringexercise.services;

import com.example._12_springdataadvancedqueringexercise.models.Author;
import com.example._12_springdataadvancedqueringexercise.models.AuthorNamesWithTotalCount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AuthorService {
    Author getRandomAuthor();

    List<Author> selectAuthorWhichNameEndsWithGivenLetter(String letterToEndWith);

    List<AuthorNamesWithTotalCount> getCountOfCopies();

    int getBookCountByGivenAuthor(String firstName, String lastName);
}
