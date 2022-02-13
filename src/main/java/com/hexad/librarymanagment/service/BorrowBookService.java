package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.constant.LibraryMgmtConstant;
import com.hexad.librarymanagment.exception.UserBorrowLimitException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowBookService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowBookService(BookRepository bookRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    public User borrowBook(Integer userId, Integer bookId) {
        User user = borrowRepository.findByUserId(userId);
        if (user != null) {
            List<Book> borrowedBooks = user.getBorrowBookList();
            if (borrowedBooks.size() < LibraryMgmtConstant.MAX_BORROW_BOOK_SIZE) {
                Book book = bookRepository.findByBookId(bookId);
                borrowedBooks.add(book);
            } else {
                throw new UserBorrowLimitException("You have exceeded borrowing limit! Can not borrow more than two books");
            }
        }
        return user;
    }
}
