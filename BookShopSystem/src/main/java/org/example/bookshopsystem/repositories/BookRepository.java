package org.example.bookshopsystem.repositories;

import org.example.bookshopsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findAllBooksByReleaseDateAfterOrderByReleaseDateDesc(LocalDate releaseDate);
}
