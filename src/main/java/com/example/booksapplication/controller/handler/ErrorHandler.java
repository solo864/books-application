package com.example.booksapplication.controller.handler;

import com.example.booksapplication.model.constants.ExceptionConstants;
import com.example.booksapplication.model.dto.ExceptionDto;
import com.example.booksapplication.model.exception.UpdateBookException;
import com.example.booksapplication.model.exception.UniquenessViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

   @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(Exception ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ExceptionConstants.UNEXPECTED_EXCEPTION_CODE, ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDto handle(NotFoundException ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(UpdateBookException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionDto handle(UpdateBookException ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionDto handle(MethodArgumentNotValidException ex) {
        log.error("Exception ", ex);
        return new ExceptionDto(ExceptionConstants.VALIDATION_EXCEPTION_CODE, ExceptionConstants.VALIDATION_EXCEPTION_MESSAGE);
    }


    @ExceptionHandler(UniquenessViolationException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionDto handle(UniquenessViolationException ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ex.getCode(), ex.getMessage());
    }

}
