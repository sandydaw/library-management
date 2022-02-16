package com.hexad.librarymanagment.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userNotFoundException) {
        super(userNotFoundException);
    }

}
