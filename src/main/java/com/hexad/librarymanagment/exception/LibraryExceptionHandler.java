package com.hexad.librarymanagment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> bookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserBorrowLimitException.class)
    public ResponseEntity<String> bookBorrowLimitException(UserBorrowLimitException userBorrowLimitException){
        return  new ResponseEntity<>(userBorrowLimitException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotfoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(Exception globalException){
        return new ResponseEntity<>(globalException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
