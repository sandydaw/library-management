package com.hexad.librarymanagment.utility;

import com.hexad.librarymanagment.model.Book;

public class LibraryUtility {
    public static void updateNoOFCopiesAfterBorrowed(Book book) {
        book.setNoOfCopies(book.getNoOfCopies() - 1);
    }
    public static void updateNoOfCopiesAfterReturnBook(Book book){
        book.setNoOfCopies(book.getNoOfCopies() + 1);
    }
}
