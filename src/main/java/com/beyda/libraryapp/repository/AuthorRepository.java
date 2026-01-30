package com.beyda.libraryapp.repository;

import com.beyda.libraryapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

