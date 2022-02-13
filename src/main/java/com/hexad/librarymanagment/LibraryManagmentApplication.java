package com.hexad.librarymanagment;

import com.hexad.librarymanagment.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LibraryManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagmentApplication.class, args);
        System.out.println(Arrays.asList(new Book(100,"Test name","testpublisher"),
                new Book(200,"Test name2","test publisher2")));
    }

}
