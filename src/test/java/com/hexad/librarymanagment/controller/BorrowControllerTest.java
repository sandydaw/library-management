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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = BorrowController.class)
public class BorrowControllerTest {

    private static final Integer BOOK_ID = 1;
    private static final Integer USER_ID = 100;

    @MockBean
    private BorrowBookService borrowBookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void borrowBook_shouldAddBookInBorrowList() throws Exception {
        given(borrowBookService.borrowBook(USER_ID,BOOK_ID)).willReturn(new User(USER_ID,"Test User",new ArrayList<>()));
        mockMvc.perform(MockMvcRequestBuilders.put("/library/borrowbooks/"+USER_ID+"/"+BOOK_ID)).
                andExpect(status().isOk());
    }

}