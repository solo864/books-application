package com.example.booksapplication.controller;

import com.example.booksapplication.model.dto.BookDto;
import com.example.booksapplication.model.dto.PublisherAndBooksListDto;
import com.example.booksapplication.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public PublisherAndBooksListDto getBooksByPublisher(@PathVariable Long id) {
        return publisherService.getAllBooksByPublisher(id);
    }


    @PostMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PUBLISHER')")
    public void addBook(@PathVariable Long id,
                                   @RequestBody @Valid BookDto bookDto) {
        publisherService.addBook(id, bookDto);
    }

}
