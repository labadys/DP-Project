package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // ОСТАВЬТЕ ТОЛЬКО ЭТОТ метод (если поле name существует)
    List<Author> findByNameContainingIgnoreCase(String name);

    // УДАЛИТЕ все методы, которые ссылаются на firstName или lastName
    // Например, удалите:
    // List<Author> findByFirstNameContainingIgnoreCase(String firstName);
    // List<Author> findByLastNameContainingIgnoreCase(String lastName);

    // Или удалите кастомные @Query с firstName/lastName
}