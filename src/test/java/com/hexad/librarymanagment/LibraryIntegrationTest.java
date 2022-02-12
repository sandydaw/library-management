package com.hexad.librarymanagment;

import com.hexad.librarymanagment.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LibraryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getBook_shouldReturnBook() throws Exception {
        //arrange

        //act
        ResponseEntity<Book> book = restTemplate.getForEntity("/books/100", Book.class);
        //assert
        assertThat(book.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(book.getBody().getBookId()).isEqualTo(anyInt());
        assertThat(book.getBody().getName()).isEqualTo(anyString());
    }
}
