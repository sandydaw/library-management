package com.hexad.librarymanagment.utility;

import com.hexad.librarymanagment.model.Book;

public class LibrayUitility {
    public static void updateNoOFCopiesAfterBorrowed(Book book) {
        book.setNoOfCopies(book.getNoOfCopies() - 1);
    }
}
