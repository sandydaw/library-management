package com.hexad.librarymanagment.service;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryServiceTest {

    private LibraryService libraryService;
    @Mock
    private BookRepository bookRepository;


    @Before
    public void setUp() {
        libraryService = new LibraryService(bookRepository);
    }

    @Test
    public void getBook_shouldReturnBook(){
        given(bookRepository.findByBookId(anyInt())).willReturn(new Book(199, "Thoughts on pakistan", "Oxford publication"));
        Book book=libraryService.getBook(199);
        assertTrue(book.getName().equalsIgnoreCase("Thoughts on pakistan"));
        assertTrue(book.getPublisher().equalsIgnoreCase("Oxford publication"));

    }

    @Test(expected = BookNotFoundException.class)
    public void getBook_shouldThrowException(){
        given(bookRepository.findById(anyInt())).willReturn(null);
        libraryService.getBook(999999);
    }
}