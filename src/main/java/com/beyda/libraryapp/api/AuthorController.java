package com.beyda.libraryapp.api;

import com.beyda.libraryapp.api.dto.AuthorResponse;
import com.beyda.libraryapp.api.mapper.ApiMapper;
import com.beyda.libraryapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorResponse> listAll() {
        return authorService.listAll().stream()
                .map(ApiMapper::toAuthorResponse)
                .toList();
    }
}