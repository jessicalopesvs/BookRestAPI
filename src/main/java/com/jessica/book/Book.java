package com.jessica.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Book {

    private String isbn;
    private String author;
    private String title;
    private int numOfPages;
    private String genre;

}
