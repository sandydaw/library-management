package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping(path = "/{bookId}")
    public Book getBook(@PathVariable int bookId){
        return libraryService.getBook(bookId);
    }
    @GetMapping(path = "/")
    public List<Book> getListOfBooks(){
        return libraryService.findAllBooks();
    }
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void bookNotFoundExceptionHandler(BookNotFoundException exception){

    }
}
