package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.exception.UserBorrowLimitException;
import com.hexad.librarymanagment.exception.UserNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowBookServiceTest {
    private final Integer USER_ID = 100;
    private final Integer NOT_FOUND_USER_ID = 99999;
    private final Integer BOOK_ID = 3;
    private final Integer NOT_FOUND_BOOK_ID = 99999;

    private BorrowBookService borrowBookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    private List<Book> books;

    @Before
    public void setup() {
        borrowBookService = new BorrowBookService(bookRepository, userRepository);
        books = new ArrayList<>();
    }

    @Test
    public void borrowBook_shouldAddBookToUser() {
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(3, "test book name3", "Test author name", "test publication3", 2));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        User user = borrowBookService.borrowBook(USER_ID, BOOK_ID);
        Optional<Book> u = user.getBorrowBookList().stream().findFirst();
        if (u.isPresent()){
            Book bk =u.get();
            assertEquals(u.get().getName(), "test book name3");
        }

    }

    @Test
    public void borrowBook_shouldThrowException() {
        books.add(new Book(2, "test book name2", "Test author name", "test publication2", 1));
        books.add(new Book(3, "test book name3", "Test author name", "test publication3", 3));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(3, "Test Book Name3", "Test author name", "Test Publication3", 1));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(UserBorrowLimitException.class, () -> borrowBookService.borrowBook(USER_ID, BOOK_ID));
    }

    @Test
    public void borrowBook_shouldChangeBookCount() {
        books.add(new Book(2, "test book name2", "Test author name", "test publication2", 1));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(3, "Test Book Name3", "Test author name", "Test Publication3", 3));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        User user = borrowBookService.borrowBook(USER_ID, 3);
        Optional<Book> book = user.getBorrowBookList().stream().filter(b -> b.getBookId().equals(3)).findFirst();
        if (book.isPresent()) {
            Book bk = book.get();
            assertEquals(bk.getNoOfCopies(), 2);
            assertEquals(user.getBorrowBookList().size(), 2);
        }

    }

    @Test
    public void borrowBook_shouldThrowBookNotFoundException() {
        given(bookRepository.findByBookId(NOT_FOUND_BOOK_ID)).willReturn(new Book(NOT_FOUND_BOOK_ID, "test book name2", "Test author name", "test publication2", 0));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(BookNotFoundException.class, () -> borrowBookService.borrowBook(USER_ID, NOT_FOUND_BOOK_ID));
    }

    @Test
    public void borrowBook_shouldThrowUserNotFoundException() {
        given(bookRepository.findByBookId(NOT_FOUND_BOOK_ID)).willReturn(new Book(NOT_FOUND_BOOK_ID, "test book name2", "Test author name", "test publication2", 0));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(UserNotFoundException.class, () -> borrowBookService.borrowBook(NOT_FOUND_USER_ID, NOT_FOUND_BOOK_ID));
    }

}