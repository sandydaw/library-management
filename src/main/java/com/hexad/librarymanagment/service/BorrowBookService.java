package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.constant.LibraryMgmtConstant;
import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.exception.UserBorrowLimitException;
import com.hexad.librarymanagment.exception.UserNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.BorrowRepository;
import com.hexad.librarymanagment.utility.LibrayUitility;
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
                LibrayUitility.updateNoOFCopiesAfterBorrowed(book);
                if (book.getNoOfCopies() < 0) {
                    throw new BookNotFoundException("Book is not present in the Library");
                }
                bookRepository.save(book);
                borrowedBooks.add(book);
            } else {
                throw new UserBorrowLimitException("You have exceeded borrowing limit! Can not borrow more than two books");
            }
        } else {
            throw new UserNotFoundException("User with id : " + userId + " is not registered in the Library. User can not borrow a book!");
        }
        return user;
    }
}
