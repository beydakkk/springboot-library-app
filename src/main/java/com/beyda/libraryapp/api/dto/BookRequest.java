package com.beyda.libraryapp.api.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank String title,
        Double price,
        String ISBN13,
        Integer publishedYear,
        @NotBlank String publisherName,
        @NotBlank String authorNameSurname
) {}
    

