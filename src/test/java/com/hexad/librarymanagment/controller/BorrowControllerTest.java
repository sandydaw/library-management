package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.BorrowBookService;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = BorrowController.class)
public class BorrowControllerTest {

    @MockBean
    private BorrowBookService borrowBookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void borrowBook_shouldAddBookInBorrowList() throws Exception {

        given(borrowBookService.borrowBook(100,1)).willReturn(new User(100,"test user1", Arrays.asList(new Book(1,"test book title2","Test author","test publication2",2),new Book(2,"test book title2","Test author","test publication2",1))));
        mockMvc.perform(MockMvcRequestBuilders.get("/borrowbooks/100/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("userId").value(100)).
                andExpect(jsonPath("name").value("test user1")).
                andExpect(jsonPath("$.borrowBookList[0].name").value("test book title2"));
    }

}