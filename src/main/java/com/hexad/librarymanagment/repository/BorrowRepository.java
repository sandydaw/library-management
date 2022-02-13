package com.hexad.librarymanagment.repository;

import com.hexad.librarymanagment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<User, Integer> {
    User findByUserId(Integer userId);
}

