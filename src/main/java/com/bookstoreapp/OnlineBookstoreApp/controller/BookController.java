package com.bookstoreapp.OnlineBookstoreApp.controller;

import com.bookstoreapp.OnlineBookstoreApp.model.BookModel;
import com.bookstoreapp.OnlineBookstoreApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a new book
    @PostMapping
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        BookModel book = bookService.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    // Get book by ID (New method added in feature-add-book branch)
    @GetMapping("/find/{id}")
    public ResponseEntity<BookModel> findBookById(@PathVariable Long id) {
        BookModel book = bookService.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }


    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel bookDetails) {
        BookModel updatedBook = bookService.updateBook(id, bookDetails);
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id) ? ResponseEntity.ok("Book deleted") : ResponseEntity.notFound().build();
    }
}

