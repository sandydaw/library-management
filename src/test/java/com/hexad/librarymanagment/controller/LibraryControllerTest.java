package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.service.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LibraryService libraryService;

    @Test
    public void getBook_shouldReturnBook() throws Exception {
        given(libraryService.getBook(anyInt())).willReturn(new Book(100,"ambedkar","publisher1"));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("name").value("ambedkar")).
                andExpect(jsonPath("publisher").value("publisher1"));
    }

}