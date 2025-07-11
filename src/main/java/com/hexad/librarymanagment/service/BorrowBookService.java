package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.constant.LibraryMgmtConstant;
import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.exception.UserBorrowLimitException;
import com.hexad.librarymanagment.exception.UserNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.UserRepository;
import com.hexad.librarymanagment.utility.LibraryUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowBookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    public BorrowBookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public User borrowBook(Integer userId, Integer bookId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            List<Book> borrowedBooks = user.getClass().;
            if (borrowedBooks.size() < LibraryMgmtConstant.MAX_BORROW_BOOK_SIZE) {
                Book book = bookRepository.findByBookId(bookId);
                LibraryUtility.updateNoOFCopiesAfterBorrowed(book);
                bookRepository.save(book);
                borrowedBooks.add(book);
                userRepository.save(user);
            } else {
                throw new UserBorrowLimitException("You have exceeded borrowing limit! Can not borrow more than two books");
            }
        } else {
            throw new UserNotFoundException("User with id : " + userId + " is not registered in the Library. User can not borrow a book!");
        }
        return user;
    }
}
