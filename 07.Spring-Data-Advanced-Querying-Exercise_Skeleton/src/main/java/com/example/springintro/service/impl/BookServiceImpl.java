package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.model.entity.enums.AgeRestriction;
import com.example.springintro.model.entity.enums.EditionType;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

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
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public int increaseBookCopiesBy(String date, int count) {

        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd MMM yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate parsed = LocalDate.parse(date, df);

        int updateBookCount = bookRepository.updateBookCopiesReleasedAfter(parsed, count);

        return updateBookCount * count;
    }

    @Override
    public BookInfo findBookByTitle(String title) {

        return this.bookRepository.findByBookTitle(title);
    }

    @Override
    public List<String> findAllBooksContaining(String str) {
        List<Book> books = this.bookRepository.findByTitleContainingIgnoreCase(str);
        return mapBooksTitles(books);
    }

    @Override
    public int findCountOfBooksTitleLengthGreaterThan(int length) {
        return this.bookRepository.countBooksByTitleLengthGreaterThan(length);
    }

    @Override
    public List<String> findAllTitlesByAuthorLastNameStartingWith(String str) {
        List<Book> books = this.bookRepository.findByAuthor_LastNameStartingWith(str);
        List<String> result = books
                .stream()
                .map(b -> String
                        .format("%s (%s %s)%n",
                                b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()))
                .toList();
        System.out.println();
        return result;
    }

    @Override
    public StringBuilder getBooksBeforeDate(String date) {
        StringBuilder result = new StringBuilder();

        LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        this.bookRepository.findByReleaseDateBefore(parsed)
                .forEach(b -> result.append(String.format("%s %s %.2f%n", b.getTitle(), b.getEditionType().toString(), b.getPrice())));

        System.out.println();
        return result;
    }

    @Override
    public List<String> findAllTitlesNotReleasedInYear(int year) {
        LocalDate firstBound = LocalDate.of(year, 1, 1);
        LocalDate secondBound = LocalDate.of(year, 12, 31);

        List<Book> resultList = this.bookRepository.findByReleaseDateBeforeOrReleaseDateAfter(firstBound, secondBound);

        return mapBooksTitles(resultList);
    }

    @Override
    public StringBuilder findTitlesWithPriceByBounds(BigDecimal lowerBound, BigDecimal upperBound) {
        StringBuilder result = new StringBuilder();
        List<Book> booksList = this.bookRepository.findByPriceLessThanOrPriceGreaterThan(lowerBound, upperBound);

        booksList.forEach(b -> result.append(String.format("%s - $%.2f%n", b.getTitle(), b.getPrice())));

        return result;
    }

    @Override
    public List<String> mapBooksTitles(List<Book> books) {
        return books.stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findAllGoldenBooksWithCopiesLessThan(String editionType, int copies) {
        List<Book> goldenBooks = this.bookRepository.findByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType.toUpperCase()), copies);

        return mapBooksTitles(goldenBooks);
    }

    @Override
    public List<String> findAllTitleByAgeRestriction(String input) {
        AgeRestriction restriction = AgeRestriction.valueOf(input.toUpperCase());

        List<Book> books = this.bookRepository.findByAgeRestriction(restriction);

        return mapBooksTitles(books);
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
