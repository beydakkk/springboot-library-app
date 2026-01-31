package com.beyda.libraryapp.service;

import com.beyda.libraryapp.api.dto.BookRequest;
import com.beyda.libraryapp.common.NotFoundException;
import com.beyda.libraryapp.domain.Author;
import com.beyda.libraryapp.domain.Book;
import com.beyda.libraryapp.domain.Publisher;
import com.beyda.libraryapp.repository.AuthorRepository;
import com.beyda.libraryapp.repository.BookRepository;
import com.beyda.libraryapp.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findByIdWithPublisherAndAuthor(id)
                .orElseThrow(() -> new NotFoundException("Book don't found: " + id));
    }

    @Transactional
    public Book create(BookRequest req) {
        Publisher publisher = publisherRepository.findByPublisherNameIgnoreCase(req.publisherName())
                .orElseGet(() -> publisherRepository.save(Publisher.builder().publisherName(req.publisherName()).build()));

        Book book = Book.builder()
                .title(req.title())
                .price(req.price())
                .ISBN13(req.ISBN13())
                .publishedYear(req.publishedYear())
                .publisher(publisher)
                .build();

        Book saved = bookRepository.save(book);

        Author author = Author.builder()
                .authorNameSurname(req.authorNameSurname())
                .book(saved)
                .build();

        authorRepository.save(author);
        saved.setAuthor(author);

        return saved;
    }

    @Transactional
    public Book update(Long id, BookRequest req) {
        Book book = getById(id);

        Publisher publisher = publisherRepository.findByPublisherNameIgnoreCase(req.publisherName())
                .orElseGet(() -> publisherRepository.save(Publisher.builder().publisherName(req.publisherName()).build()));

        book.setTitle(req.title());
        book.setPrice(req.price());
        book.setISBN13(req.ISBN13());
        book.setPublishedYear(req.publishedYear());
        book.setPublisher(publisher);

        if (book.getAuthor() == null) {
            Author author = Author.builder()
                    .authorNameSurname(req.authorNameSurname())
                    .book(book)
                    .build();
            authorRepository.save(author);
            book.setAuthor(author);
        } else {
            book.getAuthor().setAuthorNameSurname(req.authorNameSurname());
        }

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book don't found: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> findPublishedAfter2023() {
        return bookRepository.findBooksPublishedAfterWithPublisherAndAuthor(2023);
    }

    // Requirement: 'A' harfiyle başlayan kitapları getiren stream yapısı
    public List<Book> findStartingWithAStream() {
        return bookRepository.findAllWithPublisherAndAuthor().stream()
                .filter(b -> b.getTitle() != null && b.getTitle().toUpperCase().startsWith("A"))
                .toList();
    }

    public List<Book> listAllWithPublisherAndAuthor() {
        return bookRepository.findAllWithPublisherAndAuthor();
    }
}
