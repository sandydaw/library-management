package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/borrowbooks")
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @GetMapping(path = "/{userId}/{bookid}")
    public User borrowBook(@PathVariable Integer userId, @PathVariable Integer bookid) {
        return borrowBookService.borrowBook(userId, bookid);
    }
}
