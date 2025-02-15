package com.example.springintro.repository;

import com.example.springintro.model.entity.BookInfo;
import com.example.springintro.model.entity.enums.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.enums.EditionType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("UPDATE Book AS b SET b.copies = b.copies + :count WHERE b.releaseDate > :parsed")
    @Modifying
    @Transactional
    int updateBookCopiesReleasedAfter(LocalDate parsed, int count);

    @Query("SELECT COUNT(b.id) FROM Book b WHERE LENGTH(b.title) > :length")
    int countBooksByTitleLengthGreaterThan(int length);

    @Query("SELECT b.title AS title,b.editionType AS editionType,b.ageRestriction AS ageRestriction, b.price AS price " +
            "FROM Book b " +
            "WHERE b.title = :title")
    BookInfo findByBookTitle(String title);

    List<Book> findByAuthor_LastNameStartingWith(String str);

    List<Book> findByTitleContainingIgnoreCase(String str);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate date1, LocalDate date2);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);
}
