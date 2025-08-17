package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "library_orders")
public class LibraryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user; // Связь с AppUser

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // Связь с Book

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private int quantity;
}