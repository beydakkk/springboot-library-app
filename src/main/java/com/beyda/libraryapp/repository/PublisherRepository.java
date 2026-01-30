package com.beyda.libraryapp.repository;

import com.beyda.libraryapp.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByPublisherNameIgnoreCase(String publisherName);

    // Requirement : list 2 publishers with their books and authors
    @Query("select distinct p from Publisher p left join fetch p.books b left join fetch b.author a order by p.publisherId")
    List<Publisher> findPublishersWithBooksAndAuthors();
}

