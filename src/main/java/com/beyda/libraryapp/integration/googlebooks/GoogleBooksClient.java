package com.beyda.libraryapp.integration.googlebooks;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleBooksClient", url = "${googlebooks.base-url}")
public interface GoogleBooksClient {

    @GetMapping("/books/v1/volumes")
    GoogleBooksVolumesResponse search(@RequestParam("q") String query);
}
