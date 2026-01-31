package com.beyda.libraryapp.api;

import com.beyda.libraryapp.api.dto.BookRequest;
import com.beyda.libraryapp.api.dto.BookResponse;
import com.beyda.libraryapp.api.mapper.ApiMapper;
import com.beyda.libraryapp.integration.googlebooks.GoogleBooksService;
import com.beyda.libraryapp.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final GoogleBooksService googleBooksService;

    @GetMapping
    public List<BookResponse> listAll() {
        return bookService.listAllWithPublisherAndAuthor().stream()
                .map(ApiMapper::toBookResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return ApiMapper.toBookResponse(bookService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody @Valid BookRequest req) {
        return ApiMapper.toBookResponse(bookService.create(req));
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody @Valid BookRequest req) {
        return ApiMapper.toBookResponse(bookService.update(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
    
    @GetMapping("/starts-with-a")
    public List<BookResponse> startsWithA() {
        return bookService.findStartingWithAStream().stream()
                .map(ApiMapper::toBookResponse)
                .toList();
    }

    // Requirement: 2023 sonrası (JPA Query)
    @GetMapping("/published-after-2023")
    public List<BookResponse> publishedAfter2023() {
        return bookService.findPublishedAfter2023().stream()
                .map(ApiMapper::toBookResponse)
                .toList();
    }

    // Requirement: Feign client Google Books API
    @GetMapping("/google-search")
    public List<BookResponse> googleSearch(@RequestParam("title") String title) {
        return googleBooksService.searchByTitle(title);
    }
}