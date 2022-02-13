package com.hexad.librarymanagment.exception;

public class UserBorrowLimitException extends RuntimeException {
    public UserBorrowLimitException(String borrowBookException) {
        super(borrowBookException);
    }
}
