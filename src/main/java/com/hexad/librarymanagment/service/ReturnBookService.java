package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.exception.UserNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.UserRepository;
import com.hexad.librarymanagment.utility.LibraryUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnBookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReturnBookService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User returnBook(Integer userId, Integer bookId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            List<Book> bookToReturnList = user.getBorrowBookList();
            Optional<Book> bookToReturn = bookToReturnList.stream().filter(book -> book.getBookId().equals(bookId)).findFirst();
            if (bookToReturn.isPresent()) {
                Book bookReturn = bookToReturn.get();
                bookToReturnList.remove(bookReturn);
                LibraryUtility.updateNoOfCopiesAfterReturnBook(bookReturn);
                bookRepository.save(bookReturn);
                user.setBorrowBookList(bookToReturnList);
            } else {
                throw new BookNotFoundException("Book you are trying to return is not in your Borrow List");
            }
        }else{
            throw new UserNotFoundException("User with id "+userId+" is not found.Please register yourself!");
        }
        return user;
    }
}