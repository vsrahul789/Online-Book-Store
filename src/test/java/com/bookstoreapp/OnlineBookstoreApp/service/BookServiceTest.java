package com.bookstoreapp.OnlineBookstoreApp.service;

import com.bookstoreapp.OnlineBookstoreApp.model.BookModel;
import com.bookstoreapp.OnlineBookstoreApp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_shouldSaveAndReturnBook() {
        BookModel book = new BookModel();
        when(bookRepository.save(book)).thenReturn(book);

        BookModel savedBook = bookService.addBook(book);

        assertEquals(book, savedBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getAllBooks_shouldReturnListOfBooks() {
        List<BookModel> books = Arrays.asList(new BookModel(), new BookModel());
        when(bookRepository.findAll()).thenReturn(books);

        List<BookModel> retrievedBooks = bookService.getAllBooks();

        assertEquals(books, retrievedBooks);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_shouldReturnBook_whenBookExists() {
        Long bookId = 1L;
        BookModel book = new BookModel();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookModel retrievedBook = bookService.getBookById(bookId);

        assertEquals(book, retrievedBook);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void getBookById_shouldReturnNull_whenBookDoesNotExist() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookModel retrievedBook = bookService.getBookById(bookId);

        assertNull(retrievedBook);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void updateBook_shouldUpdateAndReturnBook_whenBookExists() {
        Long bookId = 1L;
        BookModel existingBook = new BookModel();
        existingBook.setId(bookId);
        existingBook.setTitle("Old Title");
        BookModel bookDetails = new BookModel();
        bookDetails.setTitle("New Title");
        bookDetails.setAuthor("New Author");
        bookDetails.setPrice(BigDecimal.valueOf(20.0));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        BookModel updatedBook = bookService.updateBook(bookId, bookDetails);

        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals(BigDecimal.valueOf(20.0), updatedBook.getPrice());
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void updateBook_shouldReturnNull_whenBookDoesNotExist() {
        Long bookId = 1L;
        BookModel bookDetails = new BookModel();
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookModel updatedBook = bookService.updateBook(bookId, bookDetails);

        assertNull(updatedBook);
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void deleteBook_shouldReturnTrue_whenBookExists() {
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);

        boolean deleted = bookService.deleteBook(bookId);

        assertTrue(deleted);
        verify(bookRepository, times(1)).existsById(bookId);
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void deleteBook_shouldReturnFalse_whenBookDoesNotExist() {
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(false);

        boolean deleted = bookService.deleteBook(bookId);

        assertFalse(deleted);
        verify(bookRepository, times(1)).existsById(bookId);
        verify(bookRepository, never()).deleteById(bookId);
    }
}