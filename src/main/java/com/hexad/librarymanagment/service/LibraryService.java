package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class LibraryService {
    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if(book == null)
            throw new BookNotFoundException("Book with id "+bookId+" is not present for now");
        return book;
    }

    @ExceptionHandler
    private void bookNotFoundExceptionHandler(BookNotFoundException exception){

    }
}
