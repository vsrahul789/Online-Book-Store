package com.bookstoreapp.OnlineBookstoreApp.repository;

import com.bookstoreapp.OnlineBookstoreApp.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
}

