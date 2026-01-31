package com.beyda.libraryapp.service;

import com.beyda.libraryapp.domain.Publisher;
import com.beyda.libraryapp.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> listAll() {
        return publisherRepository.findAll();
    }

    public List<Publisher> listTwoWithBooksAndAuthors() {
        return publisherRepository.findPublishersWithBooksAndAuthors().stream()
                .limit(2)
                .toList();
    }
}