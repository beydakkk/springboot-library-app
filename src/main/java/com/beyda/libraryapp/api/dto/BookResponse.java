package com.beyda.libraryapp.api.dto;

public record BookResponse(
        Long bookId,
        String title,
        Double price,
        String ISBN13,
        Integer publishedYear,
        String publisherName,
        String authorNameSurname
) {}