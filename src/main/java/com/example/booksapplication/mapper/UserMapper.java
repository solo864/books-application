package com.example.booksapplication.mapper;


import com.example.booksapplication.dao.entity.UserEntity;
import com.example.booksapplication.model.dto.UserRegisterDto;

public  class UserMapper {

    public static UserEntity mapRegisterDtoToEntity(UserRegisterDto dto){
        return UserEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
