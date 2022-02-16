package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.BorrowBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/library/borrowbooks")
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @ApiOperation(value = "Borrow a book for user ",
            notes = "Provide an user id and book id to borrow a book",
            response = User.class
    )
    @PutMapping(path = "/{userId}/{bookId}")
    public ResponseEntity<User> borrowBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        User user = borrowBookService.borrowBook(userId, bookId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
