package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LibraryService(UserRepository userRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Book getBook(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if (book == null) throw new BookNotFoundException("Book with id " + bookId + " is not present for now");
        return book;
    }

    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        if (savedBook == null) throw new RuntimeException("Error while saving book");
        return savedBook;

    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        if (savedUser == null) throw new RuntimeException("Error while saving user");
        return savedUser;
    }

    @ExceptionHandler
    private void bookNotFoundExceptionHandler(BookNotFoundException exception) {
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
