package com.hexad.librarymanagment.repository;

import com.hexad.librarymanagment.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    public Book findByBookId( Integer bookId);
}
