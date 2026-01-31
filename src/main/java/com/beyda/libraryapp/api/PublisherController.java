package com.beyda.libraryapp.api;

import com.beyda.libraryapp.api.dto.PublisherResponse;
import com.beyda.libraryapp.api.mapper.ApiMapper;
import com.beyda.libraryapp.service.PublisherService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public List<PublisherResponse> listAll() {
        return publisherService.listAll().stream()
                .map(p -> new PublisherResponse(
                        p.getPublisherId(),
                        p.getPublisherName()
                ))
                .toList();
    }
}
