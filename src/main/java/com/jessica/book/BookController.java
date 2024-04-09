package com.jessica.book;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class BookController {

    ArrayList<Book> books = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "I'm the book controller";
    }

    //Populating the list

    @GetMapping("/init")
    public List<Book> init() {
        books.add(new Book("978-3-16-148410-0", "J.K. Rowling", "Harry Potter and the Sorcerer's Stone", 320, "Fantasy"));
        books.add(new Book("978-1-56619-909-4", "George Orwell", "1984", 328, "Dystopian fiction"));
        books.add(new Book("978-0-547-05299-4", "Agatha Christie", "Murder on the Orient Express", 256, "Mystery"));
        books.add(new Book("978-0-394-72102-9", "Stephen King", "The Shining", 464, "Horror"));
        books.add(new Book("978-1-86092-021-9", "J.R.R. Tolkien", "The Hobbit", 310, "Fantasy"));


        return books;
    }

    //Getting the list
    @GetMapping("/books")
    public List<Book> getBooks() {

        return books;
    }


    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No book found with ISBN: " + isbn));
    }
    //post

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {

        for (Book b : books) {
            if (Objects.equals(b.getIsbn(), book.getIsbn())) {
                return "Book ISBN alredy exists";
            }
        }
        books.add(book);
        return "Success";

    }

    //update
    @PutMapping("/update")
    public String updateBook(@RequestBody Book book) {

        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                b.setIsbn(book.getIsbn());
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setNumOfPages(book.getNumOfPages());
                return "Update Success";
            }
        }

        return "Update Failed";
    }

    //delete
    @DeleteMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn) {

        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book temp = iterator.next();

            if (temp.getIsbn().equals(isbn)) {
                iterator.remove();
                return "Delete Success";
            }
        }
        return "Delete Failed";
    }


}
