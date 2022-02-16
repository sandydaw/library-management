package com.hexad.librarymanagment.controller;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import com.hexad.librarymanagment.service.ReturnBookService;
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
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = ReturnBookController.class)
public class ReturnBookControllerTest {
    private static final Integer RETURN_BOOK_ID = 100;
    private static final Integer USER_ID = 1;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ReturnBookService returnBookService;

    private List<Book> borrowBookList;

    @Before
    public void setup(){
        borrowBookList=new ArrayList<>();
    }

    @Test
    public void returnBook_shouldDecreamentBookFromBorrowList() throws Exception {
        borrowBookList.add(new Book(RETURN_BOOK_ID,"TestBookName1","Test Auther name","TestBookPublication",3));
        given(returnBookService.returnBook(USER_ID,RETURN_BOOK_ID)).willReturn(new User(USER_ID,"TestUserName",borrowBookList));
        mockMvc.perform(MockMvcRequestBuilders.put("/returnbook/"+USER_ID+"/"+RETURN_BOOK_ID)).
                andExpect(status().isOk());
    }

}