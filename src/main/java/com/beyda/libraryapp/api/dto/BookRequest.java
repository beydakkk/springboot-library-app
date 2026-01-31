package com.beyda.libraryapp.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record BookRequest(
        @NotBlank String title,
        BigDecimal price,
        String ISBN13,
        Integer publishedYear,
        @NotBlank String publisherName,
        @NotBlank String authorNameSurname
) {}
    

