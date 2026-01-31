package com.beyda.libraryapp.integration.googlebooks;

import com.beyda.libraryapp.api.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GoogleBooksService {

    private final GoogleBooksClient client;

    public List<BookResponse> searchByTitle(String titleQuery) {
        GoogleBooksVolumesResponse res = client.search(titleQuery);

        if (res == null || res.items() == null) {
            return Collections.emptyList();
        }

        return res.items().stream()
                .map(item -> {
                    String title = item.volumeInfo() != null ? item.volumeInfo().title() : null;
                    String publisher = item.volumeInfo() != null ? item.volumeInfo().publisher() : null;
                    String author = (item.volumeInfo() != null && item.volumeInfo().authors() != null && !item.volumeInfo().authors().isEmpty())
                            ? item.volumeInfo().authors().get(0)
                            : null;

                    String isbn13 = null;
                    if (item.volumeInfo() != null && item.volumeInfo().industryIdentifiers() != null) {
                        isbn13 = item.volumeInfo().industryIdentifiers().stream()
                                .filter(ii -> "ISBN_13".equalsIgnoreCase(ii.type()))
                                .map(GoogleBooksVolumesResponse.IndustryIdentifier::identifier)
                                .findFirst()
                                .orElse(null);
                    }

                    Double price = (item.saleInfo() != null && item.saleInfo().listPrice() != null)
                            ? item.saleInfo().listPrice().amount()
                            : null;
                    
                    BigDecimal priceBd = (price != null) ? BigDecimal.valueOf(price.doubleValue()) : null;

                    return new BookResponse(null, title, priceBd, isbn13, null, publisher, author);
                })
                .toList();
    }
}
