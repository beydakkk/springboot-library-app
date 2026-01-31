package com.beyda.libraryapp.unittest;

import com.beyda.libraryapp.domain.Book;
import com.beyda.libraryapp.domain.Publisher;
import com.beyda.libraryapp.integration.googlebooks.GoogleBooksService;
import com.beyda.libraryapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = com.beyda.libraryapp.api.BookController.class)
class BookControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean BookService bookService;
    @MockBean GoogleBooksService googleBooksService;

    @Test
    void startsWithA() throws Exception {
        Publisher p = Publisher.builder().publisherId(1L).publisherName("P").build();

        Book a1 = Book.builder().bookId(1L).title("A Tale").publisher(p).build();
        Book a2 = Book.builder().bookId(2L).title("a brief history").publisher(p).build();
        Book b  = Book.builder().bookId(3L).title("Book").publisher(p).build();

        when(bookService.findStartingWithAStream()).thenReturn(List.of(a1, a2));

        mockMvc.perform(get("/api/books/starts-with-a").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].title").value(
                        org.hamcrest.Matchers.everyItem(
                                org.hamcrest.Matchers.matchesPattern("(?i)^a.*")
                        )
                ))

                .andExpect(jsonPath("$[*].title").value(
                        org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem("Book"))
                ));
    }
}
