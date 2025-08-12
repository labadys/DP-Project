package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Collection<Object> findByTitleContainingIgnoreCase(String title);

    Collection<Object> findByAuthorId(Long authorId);
}
