package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping(path = "/books/{bookId}")
    public Book getBook(@PathVariable int bookId){
        return libraryService.getBook(bookId);
    }
}
