package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    Author getRandomAuthor();
}
