package com.example.booksapplication.model.constants;

public class ExceptionConstants {

   public static final String VALIDATION_EXCEPTION_CODE ="INVALID_MANDATORY_FIELD";
    public static final String VALIDATION_EXCEPTION_MESSAGE = "One or more required fields in the request are invalid";

    public static final String VALIDATION_MESSAGE = "This %s %s %s already exist";

    public static final String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED_EXCEPTION";
    public static final String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occurred";

    public static final String NOT_FOUND_CODE = "%s_NOT_FOUND";
    public static final String NOT_FOUND_MESSAGE = "%s with id %s not found";


    public static final String BOOK_UPDATE_CODE = "UPDATE_BOOK_EXCEPTION";
    public static final String BOOK_UPDATE_MESSAGE = "You can't update this because you are not the publisher of this book"; 
}
