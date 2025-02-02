package org.example.bookshopsystem.services.Impls;

import org.example.bookshopsystem.models.Author;
import org.example.bookshopsystem.models.Book;
import org.example.bookshopsystem.models.Category;
import org.example.bookshopsystem.models.enums.AgeRestriction;
import org.example.bookshopsystem.models.enums.EditionType;
import org.example.bookshopsystem.repositories.BookRepository;
import org.example.bookshopsystem.services.AuthorService;
import org.example.bookshopsystem.services.BookService;
import org.example.bookshopsystem.services.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private static final String BOOK_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOK_PATH))
                .forEach(line -> {

                    String[] split = line.split("\\s+");

                    Author author = authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(split[0])];
                    LocalDate releaseDate = LocalDate.parse(split[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(split[2]);
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(split[3]));
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(split[4])];

                    String title = Arrays.stream(split)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    categoryService.getRandomCategories();

                    Set<Category> categories = categoryService.getRandomCategories();

                    Book book = new Book(ageRestriction, releaseDate, copies, price, editionType, title, author);
                    bookRepository.save(book);

                });
    }

    @Override
    public Set<Book> getBooksAfter() {
        return bookRepository.findAllBooksByReleaseDateAfterOrderByReleaseDateDesc(LocalDate.of(2000,12,31));
    }
}
