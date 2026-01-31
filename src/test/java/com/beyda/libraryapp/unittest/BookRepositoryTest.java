package com.beyda.libraryapp.unittest;

import com.beyda.libraryapp.domain.Author;
import com.beyda.libraryapp.domain.Book;
import com.beyda.libraryapp.domain.Publisher;
import com.beyda.libraryapp.repository.AuthorRepository;
import com.beyda.libraryapp.repository.BookRepository;
import com.beyda.libraryapp.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired BookRepository bookRepository;
    @Autowired PublisherRepository publisherRepository;
    @Autowired AuthorRepository authorRepository;

    @Test
    void shouldFindBooksPublishedAfter2023() {
        Publisher p = publisherRepository.save(Publisher.builder().publisherName("P1").build());

        Book b2022 = bookRepository.save(Book.builder().title("Old").publishedYear(2022).publisher(p).build());
        authorRepository.save(Author.builder().authorNameSurname("A1").book(b2022).build());

        Book b2024 = bookRepository.save(Book.builder().title("New").publishedYear(2024).publisher(p).build());
        authorRepository.save(Author.builder().authorNameSurname("A2").book(b2024).build());

        var result = bookRepository.findBooksPublishedAfter(2023);

        assertThat(result).extracting(Book::getTitle).containsExactly("New");
    }
}
