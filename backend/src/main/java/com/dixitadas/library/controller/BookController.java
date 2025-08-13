package com.dixitadas.library.controller;

import com.dixitadas.library.model.Book;
import com.dixitadas.library.repository.BookRepository;
import com.dixitadas.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setGenre(book.getGenre());
                    existingBook.setDescription(book.getDescription());
                    existingBook.setPublishedYear(book.getPublishedYear());

                    Book savedBook = bookRepository.save(existingBook);
                    return ResponseEntity.ok(savedBook);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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
