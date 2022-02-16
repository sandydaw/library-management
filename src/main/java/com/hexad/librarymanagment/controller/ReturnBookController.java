package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/library/returnbook")
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;
    @PutMapping(path = "/{userId}/{bookId}")
    public ResponseEntity<User> returnBook(@PathVariable Integer userId, @PathVariable Integer bookId){
        User user =returnBookService.returnBook(userId,bookId);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
