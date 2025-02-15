package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();
//        List<String> getAllTitleByAgeRestriction = bookService.findAllTitleByAgeRestriction("miNoR");
//        List<String> getGoldenBooks = bookService.findAllGoldenBooksWithCopiesLessThan("gOlD", 5000);
//        printBooksByBounds();
//        printBooksNotReleasedInYear(2000);
//        printBooksReleasedBefore();
//        printAuthorsWithFirstNameEndingWith();
//        printBooksContaining("WOR");
//        printTitlesByAuthorsLastNameStartingWith("Ric");
//        System.out.println(this.bookService.findCountOfBooksTitleLengthGreaterThan(40));
//        List<String> allAuthorsAndCopies = authorService.findAuthorsByTotalBookCopies();
//        printBookInfo();
//        int count = bookService.increaseBookCopiesBy("12 Oct 2005", 100);

    }

    private void printBookInfo() {
        BookInfo bookInfo = bookService.findBookByTitle("Things Fall Apart");
        System.out.printf("%s %s %s %.2f%n", bookInfo.getTitle(), bookInfo.getEditionType(), bookInfo.getAgeRestriction(), bookInfo.getPrice());
    }

    private void printTitlesByAuthorsLastNameStartingWith(String str) {
        System.out.println(bookService.findAllTitlesByAuthorLastNameStartingWith(str));
    }

    private void printBooksContaining(String str) {
        bookService.findAllBooksContaining(str).forEach(System.out::println);
    }

    private void printAuthorsWithFirstNameEndingWith() {
        authorService.findAuthorsByNameEndingWith("e").forEach(System.out::println);
    }

    private void printBooksReleasedBefore() {
        StringBuilder result = bookService.getBooksBeforeDate("30-12-1989");
        System.out.println(result.toString());
    }

    private void printBooksNotReleasedInYear(int year) {
        bookService.findAllTitlesNotReleasedInYear(2000).forEach(System.out::println);

    }


    private void printBooksByBounds() {
        StringBuilder sb = bookService.findTitlesWithPriceByBounds(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        System.out.println(sb.toString());
    }


    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
