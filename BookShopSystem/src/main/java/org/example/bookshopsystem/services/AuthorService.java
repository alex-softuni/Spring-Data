package org.example.bookshopsystem.services;


import org.example.bookshopsystem.models.Author;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<Author> getAuthorsByBooksCountDesc();

    List<Author> getAuthorsByBooksReleaseDateBefore(LocalDate date);
}
