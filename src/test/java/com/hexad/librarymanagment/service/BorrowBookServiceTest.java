package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.UserBorrowLimitException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.repository.BookRepository;
import com.hexad.librarymanagment.repository.BorrowRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowBookServiceTest {
    private final Integer USER_ID = 100;
    private final Integer BOOK_ID = 3;
    private BorrowBookService borrowBookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowRepository borrowRepository;
    private List<Book>  books;
    @Before
    public void setup() {
        borrowBookService = new BorrowBookService(bookRepository, borrowRepository);
        books = new ArrayList<Book>();
        books.add(new Book(1, "test book name1", "test publication1"));
    }

    @Test
    public void borrowBook_shouldAddBookToUser() {
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(3, "test book name3", "test publication3"));
        given(borrowRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        borrowBookService.borrowBook(USER_ID, BOOK_ID);
    }

    @Test
    public void borrowBook_shouldThrowException() {
        books.add(new Book(2, "test book name2", "test publication2"));
        given(bookRepository.findByBookId(BOOK_ID)).willReturn(new Book(3, "Test Book Name3", "Test Publication3"));
        given(borrowRepository.findByUserId(USER_ID)).willReturn(new User(USER_ID, "test user name", books));
        assertThrows(UserBorrowLimitException.class, () -> borrowBookService.borrowBook(USER_ID, BOOK_ID));
    }


}