package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping(path = "/books/{bookId}")
    public Book getBook(@PathVariable int bookId){
        return libraryService.getBook(bookId);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void bookNotFoundExceptionHandler(BookNotFoundException exception){

    }
}
