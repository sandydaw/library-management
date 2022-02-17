package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.LibraryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = LibraryController.class)
public class LibraryControllerTest {

    private static final Integer BOOK_ID = 1;
    private final Integer USER_ID = 1;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LibraryService libraryService;
    private List<Book> books;

    @Before
    public void setup() {
        books = new ArrayList<>();
    }

    @Test
    public void getBook_shouldReturnBook() throws Exception {
        given(libraryService.getBook(anyInt())).willReturn(new Book(100, "Test book name ", "Test author name", "publisher1", 1));
        mockMvc.perform(MockMvcRequestBuilders.get("/library/books/100")).
                andExpect(status().isOk()).
                andExpect(jsonPath("name").value("Test book name ")).
                andExpect(jsonPath("publisher").value("publisher1"));
    }

    @Test
    public void getbook_shoudThrowException() throws Exception {
        given(libraryService.getBook(anyInt())).willThrow(new BookNotFoundException("Boot is not present in the library"));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/200")).
                andExpect(status().isNotFound());
    }

    @Test
    public void getAllBooks_shouldReturnListOfBooks() throws Exception {
        given(libraryService.getAllBooks()).willReturn(Arrays.asList(new Book(100, "Test book Name ", "Test author name", "publisher", 1),
                new Book(200, "book name2", "Test auther name", "some publisher", 1)));
        mockMvc.perform(MockMvcRequestBuilders.get("/library/books/")).
                andExpect(jsonPath("$[0].name").value("Test book Name ")).
                andExpect(jsonPath("$[1].publisher").value("some publisher"));
    }

    @Test
    public void getUser_shouldReturnUser() throws Exception {
        given(libraryService.getUserById(USER_ID)).willReturn(new User(USER_ID, "test name", books));
        mockMvc.perform(MockMvcRequestBuilders.get("/library/users/" + USER_ID)).
                andExpect(status().isOk()).
                andExpect(jsonPath("name").value("test name"));
    }

    @Test
    public void getBook_shouldThrowUserNotFoundException() throws Exception {
        given(libraryService.getBook(BOOK_ID)).willReturn(new Book(BOOK_ID,"book name","Author name","Publisher name",10));
        mockMvc.perform(MockMvcRequestBuilders.get("/library/books/" + BOOK_ID)).
                andExpect(status().isOk()).
                andExpect(jsonPath("name").value("book name")).
                andExpect(jsonPath("author").value("Author name")).
                andExpect(jsonPath("noOfCopies").value(10));
    }
}

