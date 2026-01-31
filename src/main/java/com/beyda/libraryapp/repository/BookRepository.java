package com.beyda.libraryapp.repository;

import com.beyda.libraryapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Requirement: 2023'den sonra basılan kitaplar
    @Query("select b from Book b where b.publishedYear > :year")
    List<Book> findBooksPublishedAfter(int year);

    @Query("select b from Book b join fetch b.publisher p left join fetch b.author a where b.bookId = :id")
    Optional<Book> findByIdWithPublisherAndAuthor(@Param("id") Long id);

    @Query("select b from Book b join fetch b.publisher p left join fetch b.author a where b.publishedYear > :year")
    List<Book> findBooksPublishedAfterWithPublisherAndAuthor(@Param("year") int year);

    // Requirement: list 2 publishers with their books and authors -> fetch graph handled in PublisherRepository query
    @Query("select b from Book b join fetch b.publisher p left join fetch b.author a")
    List<Book> findAllWithPublisherAndAuthor();
}
