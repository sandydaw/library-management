package com.hexad.librarymanagment.repository;

import com.hexad.librarymanagment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserId(Integer userId);
}

