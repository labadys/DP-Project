package com.example.library.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
