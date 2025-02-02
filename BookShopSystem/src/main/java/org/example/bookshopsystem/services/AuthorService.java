package org.example.bookshopsystem.services;


import org.example.bookshopsystem.models.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
