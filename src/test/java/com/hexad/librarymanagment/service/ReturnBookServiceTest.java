package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReturnBookServiceTest {
    private final Integer USER_ID = 100;
    private final Integer NOT_FOUND_USER_ID = 99999;
    private final Integer BOOK_ID = 3;
    private final Integer NOT_FOUND_BOOK_ID = 99999;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;

    private ReturnBookService returnBookService;
    private List<Book> books;

    @Before
    public void setup() {
        returnBookService = new ReturnBookService(userRepository, bookRepository);
        books = new ArrayList<>();
    }

    @Test
    public void returnBook_shouldRemoveBookFromBorrowListOfUser() {
        books.add(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        User user = returnBookService.returnBook(USER_ID, BOOK_ID);
        assertEquals(0, user.getBorrowBookList().size());
    }

    @Test
    public void returnBook_shouldDecrementCountByOneOfBook() {
        books.add(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        User user = returnBookService.returnBook(USER_ID, BOOK_ID);
        Optional<Book> returnBook = user.getBorrowBookList().stream().filter(b -> b.getBookId().equals( BOOK_ID)).findFirst();
        if (returnBook.isPresent()) {
            Book b = returnBook.get();
            assertEquals(1, b.getNoOfCopies() - 1);
        }
    }

    @Test
    public void returnBook_shouldThrowExceptionWhenBorrowListIsEmpty() {
        books.add(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(BookNotFoundException.class, () -> returnBookService.returnBook(USER_ID, NOT_FOUND_BOOK_ID));
    }

    public void returnBook_shouldThrowExceptionWhenNotAValidUser() {
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(BookNotFoundException.class, () -> returnBookService.returnBook(USER_ID, BOOK_ID));
        books.add(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(BOOK_ID, "test book name3","Test author name", "test publication3", 2));
        given(userRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(UserNotFoundException.class, () -> returnBookService.returnBook(NOT_FOUND_USER_ID, BOOK_ID));
    }
}