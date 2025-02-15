package org.example.bookshopsystem;

import org.example.bookshopsystem.models.Author;
import org.example.bookshopsystem.models.Book;
import org.example.bookshopsystem.services.AuthorService;
import org.example.bookshopsystem.services.BookService;
import org.example.bookshopsystem.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

//        authorService.seedAuthors();
//        categoryService.seedCategories();
//        bookService.seedBooks();
//        bookService.getBooksAfter().forEach(b -> System.out.println(b.getReleaseDate()));
//        authorService.getAuthorsByBooksReleaseDateBefore(LocalDate.of(1990, 1, 1))
//                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
//        authorService.getAuthorsByBooksCountDesc()
//                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " books count: " + a.getBooks().size()));
        this.bookService.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getReleaseDate() + " " + b.getCopies() + " copies"));

    }
}
