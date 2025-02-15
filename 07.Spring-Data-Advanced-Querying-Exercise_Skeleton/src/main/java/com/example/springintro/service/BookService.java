package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;


    int increaseBookCopiesBy(String date, int amount);

    BookInfo findBookByTitle(String title);

    List<String> findAllBooksContaining(String str);

    int findCountOfBooksTitleLengthGreaterThan(int length);

    List<String> findAllTitlesByAuthorLastNameStartingWith(String str);

    StringBuilder getBooksBeforeDate(String date);

    List<String> findAllTitlesNotReleasedInYear(int year);

    StringBuilder findTitlesWithPriceByBounds(BigDecimal lowerBound, BigDecimal upperBound);

    List<String> mapBooksTitles(List<Book> books);

    List<String> findAllGoldenBooksWithCopiesLessThan(String editionType, int copies);

    List<String> findAllTitleByAgeRestriction(String input);

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

}
