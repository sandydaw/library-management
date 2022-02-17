package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.LibraryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @ApiOperation(value = "Provide a book id  ",
            notes = "Give book details in response",
            response = User.class
    )
    @GetMapping(path = "/books/{bookId}")
    public Book getBook(@PathVariable int bookId) {
        return libraryService.getBook(bookId);
    }


    @ApiOperation(value = "Save a book ",
            notes = "Displays a book saved in library database",
            response = User.class
    )
    @PutMapping(path = "/books/savebook")
    public Book saveBook(@RequestBody Book book) {
        return libraryService.saveBook(book);
    }


    @ApiOperation(value = "Give user details to be saved in library database",
            notes = "Displays saved user in library",
            response = User.class
    )
    @PutMapping(path = "/saveuser")
    public User saveUser(@RequestBody User user) {
        return libraryService.saveUser(user);
    }

    @GetMapping(path = "/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @ApiOperation(value = "Provide a user id ",
            notes = "Show you user details of the user",
            response = User.class
    )
    @GetMapping(path = "/users/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return libraryService.getUserById(userId);
    }
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return libraryService.getAllUsers();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void bookNotFoundExceptionHandler(BookNotFoundException exception) {

    }
}
