package com.example.booksapplication.mapper;



import com.example.booksapplication.dao.entity.AuthorEntity;
import com.example.booksapplication.model.dto.AuthorAndBookDetailsDto;

import java.util.stream.Collectors;

public class AuthorMapper {


    public static AuthorAndBookDetailsDto mapAuthorBooksEntitiesToDtos(AuthorEntity entity) {
        return AuthorAndBookDetailsDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDay(entity.getBirthDay())
                .authorBooksDto(entity.getBooks().stream()
                        .map(BookMapper::mapEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }


}
