package com.example.booksapplication.model.dto;

import com.example.booksapplication.model.enums.Genre;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Builder
public class BookDto {

    @NotBlank
    @NotNull
    @Size(min = 4)
    private String name;

    @Min(value = 1940)
    @Max(value = 2022)
    private Integer publishingYear;

    @Min(value = 100)
    @Max(value = 800)
    private Integer pageCount;

    @NotNull
    private Genre genre;

}
