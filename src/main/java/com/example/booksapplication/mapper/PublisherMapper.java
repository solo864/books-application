package com.example.booksapplication.mapper;




import com.example.booksapplication.dao.entity.PublisherEntity;
import com.example.booksapplication.model.dto.PublisherAndBooksListDto;

import java.util.stream.Collectors;


public class PublisherMapper {


    public static PublisherAndBooksListDto mapEntityToPublisherBooksDto(PublisherEntity entity){
        return PublisherAndBooksListDto.builder()
                .name(entity.getName())
                .bookListDto(entity.getBooks().stream()
                        .map(BookMapper::mapEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
