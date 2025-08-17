package com.example.library.repository;


import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<com.example.library.entity.LibraryOrder, Long>  {
}