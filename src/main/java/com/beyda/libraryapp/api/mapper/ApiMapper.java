package com.beyda.libraryapp.api.mapper;

import com.beyda.libraryapp.api.dto.AuthorResponse;
import com.beyda.libraryapp.api.dto.BookResponse;
import com.beyda.libraryapp.domain.Author;
import com.beyda.libraryapp.domain.Book;

public final class ApiMapper {
    private ApiMapper() {}

    public static BookResponse toBookResponse(Book book) {
        String publisherName = book.getPublisher() != null ? book.getPublisher().getPublisherName() : null;
        String authorName = (book.getAuthor() != null) ? book.getAuthor().getAuthorNameSurname() : null;

        return new BookResponse(
                book.getBookId(),
                book.getTitle(),
                book.getPrice(),
                book.getISBN13(),
                book.getPublishedYear(),
                publisherName,
                authorName
        );
    }

    public static AuthorResponse toAuthorResponse(Author author) {
        return new AuthorResponse(
                author.getAuthorId(),
                author.getAuthorNameSurname()
        );
    }
}