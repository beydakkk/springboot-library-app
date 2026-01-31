package com.beyda.libraryapp.api.dto;

import java.math.BigDecimal;

public record BookResponse(
        Long bookId,
        String title,
        BigDecimal price,
        String ISBN13,
        Integer publishedYear,
        String publisherName,
        String authorNameSurname
) {}