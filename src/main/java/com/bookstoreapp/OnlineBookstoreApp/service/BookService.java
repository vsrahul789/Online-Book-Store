package com.bookstoreapp.OnlineBookstoreApp.service;

import com.bookstoreapp.OnlineBookstoreApp.model.BookModel;
import com.bookstoreapp.OnlineBookstoreApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add a new book
    public BookModel addBook(BookModel book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public BookModel getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Update book
    public BookModel updateBook(Long id, BookModel bookDetails) {
        Optional<BookModel> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            BookModel book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setPublishedDate(bookDetails.getPublishedDate());
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    // Delete book
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
