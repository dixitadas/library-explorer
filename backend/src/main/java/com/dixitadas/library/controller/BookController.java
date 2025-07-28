package com.dixitadas.library.controller;

import com.dixitadas.library.model.Book;
import com.dixitadas.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<Book> searchBookByTitle(String title) {
        return bookService.searchBooks(title);
    }
    
    @GetMapping("/search/{id}")
    public Optional<Book> searchById(@PathVariable Long id) {
        return bookService.searchById(id);
    }

    @GetMapping("/searchAll")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }
}
