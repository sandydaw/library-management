package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.service.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LibraryService libraryService;

    @Test
    public void getBook_shouldReturnBook() throws Exception {
        given(libraryService.getBook(anyInt())).willReturn(new Book(100, "ambedkar", "publisher1"));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/100")).
                andExpect(status().isOk()).
                andExpect(jsonPath("name").value("ambedkar")).
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
        given(libraryService.findAllBooks()).willReturn(Arrays.asList(new Book(100,"somename","publisher"),
                new Book(200,"book name2","some publisher")));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/")).
                andExpect(jsonPath("$[0].name").value("somename")).
                andExpect(jsonPath("$[1].publisher").value("some publisher"));
    }

}