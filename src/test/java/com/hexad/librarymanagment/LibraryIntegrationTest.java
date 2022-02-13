package com.hexad.librarymanagment;

import com.hexad.librarymanagment.exception.BookNotFoundException;
import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LibraryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookRepository repository;

    @Test
    public void getBook_shouldReturnBook() throws BookNotFoundException {
        //act
        ResponseEntity<Book> book = restTemplate.getForEntity("/books/100", Book.class);
        Book book1 = repository.findByBookId(100);
        //assert
        assertThat(book.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
