package com.hexad.librarymanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Integer bookId;
    private String name;
    private String author;
    private String publisher;
    @NonNull
    private Integer noOfCopies;
}
