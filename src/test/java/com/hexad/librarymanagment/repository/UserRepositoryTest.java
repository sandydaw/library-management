package com.hexad.librarymanagment.repository;

import com.hexad.librarymanagment.model.Book;
import com.hexad.librarymanagment.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    private List<Book> bookList;

    @Before
    public void setup() {
        bookList = new ArrayList<>();
    }

    @Test
    public void findByUserId_shouldReturnUser() {
        bookList.add(new Book(100, "God of small things", "Arundhati Roy", "Oxford publications", 1));
        bookList.add(new Book(200, "Test book name 2", "Test Auther", "Test publications", 4));
        testEntityManager.persistAndFlush(new User(1, "Test user name ", bookList));
        User user = userRepository.findByUserId(1);
        int userId = user.getUserId();
        Optional<Book> bk = user.getBorrowBookList().stream().filter(u -> u.getBookId() == 200).findFirst();
        if (bk.isPresent()) {
            Book borrowedBook = bk.get();
            assertEquals(1, userId);
            assertEquals(2, user.getBorrowBookList().size());
            assertEquals("Test book name 2", borrowedBook.getName());
        }
    }
}