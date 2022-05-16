package com.example.booksapplication.model.exception;

import lombok.Getter;

@Getter
public class UpdateBookException extends RuntimeException{

    private final String code;

    public UpdateBookException(String message, String code) {
        super(message);
        this.code = code;
    }
}
