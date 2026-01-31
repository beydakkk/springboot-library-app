package com.beyda.libraryapp.service;

import com.beyda.libraryapp.domain.Author;
import com.beyda.libraryapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> listAll() {
        return authorRepository.findAll();
    }
}