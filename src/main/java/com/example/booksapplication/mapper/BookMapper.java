package com.example.booksapplication.mapper;


import com.example.booksapplication.dao.entity.BookEntity;
import com.example.booksapplication.model.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {


    public static BookDto mapEntityToDto(BookEntity entity) {
        return BookDto.builder()
                .name(entity.getName())
                .publishingYear(entity.getPublishingYear())
                .pageCount(entity.getPageCount())
                .genre(entity.getGenre())
                .build();
    }


    public static List<BookDto> mapEntitiesToListDto(List<BookEntity> entities) {
        return entities.stream().map(BookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }


    public static BookEntity mapDtoToEntity(BookDto dto) {
        return BookEntity.builder()
                .name(dto.getName())
                .genre(dto.getGenre())
                .publishingYear(dto.getPublishingYear())
                .pageCount(dto.getPageCount())
                .build();
    }

}
