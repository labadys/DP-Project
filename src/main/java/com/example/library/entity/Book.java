package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer publicationYear;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;           // Объект Author

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;     // Объект Publisher

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();  // Коллекция Genre, а не одиночный genre
}