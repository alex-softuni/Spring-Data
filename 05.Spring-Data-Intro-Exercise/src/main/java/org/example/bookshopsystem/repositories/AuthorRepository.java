package org.example.bookshopsystem.repositories;

import org.example.bookshopsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> getAuthorsByBooksReleaseDateBefore(LocalDate booksReleaseDateBefore);

    @Query("SELECT a FROM Author a ORDER BY SIZE(a.books) DESC")
    List<Author> findAllByBooksOrderByBooksCountDesc();

    //3.	Get all authors, ordered by the number of their books (descending).
    // Print their first name, last name and book count.
}
