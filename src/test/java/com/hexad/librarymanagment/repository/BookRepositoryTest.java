package com.hexad.librarymanagment.repository;

import com.hexad.librarymanagment.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findById_shouldRetunBook()
    {
        Book savedBook = testEntityManager.persistAndFlush(new Book(100,"God of small things", "Oxford publications"));
        Book book = bookRepository.findByBookId(100);
        assertTrue(book.getName().equalsIgnoreCase(savedBook.getName()));
        assertTrue(book.getPublisher().equalsIgnoreCase(savedBook.getPublisher()));
    }
}