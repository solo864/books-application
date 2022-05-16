package com.example.booksapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageableBookDto {

    private List<BookDto> books;
    private int lastPageNumber;
    private boolean hasNextPage;

}
