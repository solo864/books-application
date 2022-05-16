package com.example.booksapplication.model.exception;

import lombok.Getter;

@Getter
public class UniquenessViolationException extends RuntimeException {

    private final String code;

    public UniquenessViolationException(String message, String code) {
        super(message);
        this.code = code;
    }
}
