package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT a FROM Author a WHERE LOWER(a.firstName) LIKE LOWER(concat('%', :namePart,'%')) OR LOWER(a.lastName) LIKE LOWER(concat('%', :namePart,'%'))")
    List<Author> findByNameContainingIgnoreCase(@Param("namePart") String namePart);

    @Query("SELECT a, COUNT(b) FROM Author a LEFT JOIN a.books b GROUP BY a")
    List<Object[]> findAuthorsWithBookCount();

    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE b.publicationYear > :year")
    List<Author> findAuthorsWithBooksAfterYear(@Param("year") int year);
}