package org.example.bookshopsystem.services;

import org.example.bookshopsystem.models.Author;
import org.example.bookshopsystem.models.Book;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    Set<Book> getBooksAfter();

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

}

