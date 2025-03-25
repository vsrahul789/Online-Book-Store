package com.bookstoreapp.OnlineBookstoreApp.controller;

import com.bookstoreapp.OnlineBookstoreApp.model.BookModel;
import com.bookstoreapp.OnlineBookstoreApp.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_shouldReturnOkAndBook() {
        BookModel book = new BookModel();
        when(bookService.addBook(book)).thenReturn(book);

        ResponseEntity<BookModel> response = bookController.addBook(book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).addBook(book);
    }

    @Test
    void getAllBooks_shouldReturnOkAndListOfBooks() {
        List<BookModel> books = Arrays.asList(new BookModel(), new BookModel());
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<BookModel>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_shouldReturnOkAndBook_whenBookExists() {
        Long bookId = 1L;
        BookModel book = new BookModel();
        when(bookService.getBookById(bookId)).thenReturn(book);

        ResponseEntity<BookModel> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).getBookById(bookId);
    }

    @Test
    void getBookById_shouldReturnNotFound_whenBookDoesNotExist() {
        Long bookId = 1L;
        when(bookService.getBookById(bookId)).thenReturn(null);

        ResponseEntity<BookModel> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).getBookById(bookId);
    }

    @Test
    void updateBook_shouldReturnOkAndUpdatedBook_whenBookExists() {
        Long bookId = 1L;
        BookModel bookDetails = new BookModel();
        BookModel updatedBook = new BookModel();
        when(bookService.updateBook(bookId, bookDetails)).thenReturn(updatedBook);

        ResponseEntity<BookModel> response = bookController.updateBook(bookId, bookDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
        verify(bookService, times(1)).updateBook(bookId, bookDetails);
    }

    @Test
    void updateBook_shouldReturnNotFound_whenBookDoesNotExist() {
        Long bookId = 1L;
        BookModel bookDetails = new BookModel();
        when(bookService.updateBook(bookId, bookDetails)).thenReturn(null);

        ResponseEntity<BookModel> response = bookController.updateBook(bookId, bookDetails);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).updateBook(bookId, bookDetails);
    }

    @Test
    void deleteBook_shouldReturnOk_whenBookDeleted() {
        Long bookId = 1L;
        when(bookService.deleteBook(bookId)).thenReturn(true);

        ResponseEntity<String> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted", response.getBody());
        verify(bookService, times(1)).deleteBook(bookId);
    }

    @Test
    void deleteBook_shouldReturnNotFound_whenBookDoesNotExist() {
        Long bookId = 1L;
        when(bookService.deleteBook(bookId)).thenReturn(false);

        ResponseEntity<String> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).deleteBook(bookId);
    }
}