package org.example.bookshopsystem.services.Impls;

import org.example.bookshopsystem.models.Author;
import org.example.bookshopsystem.repositories.AuthorRepository;
import org.example.bookshopsystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String AUTHORS_PATH = "src/main/resources/files/authors.txt";

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(AUTHORS_PATH))
                .forEach(line -> {

                    String[] data = line.split("\\s+");

                    Author author = new Author(data[0], data[1]);
                    authorRepository.save(author);

                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<Author> getAuthorsByBooksCountDesc() {
        return this.authorRepository.findAllByBooksOrderByBooksCountDesc();

    }


    @Override
    public List<Author> getAuthorsByBooksReleaseDateBefore(LocalDate date) {
        return this.authorRepository.getAuthorsByBooksReleaseDateBefore(date);
    }


}
