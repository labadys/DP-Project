package com.example.library.controller;

import com.example.library.entity.LibraryOrder;
import com.example.library.service.BookService;
import com.example.library.service.LibraryOrderService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders") // Проверьте этот путь!
public class LibraryOrderController {

    private final LibraryOrderService libraryOrderService;

    public LibraryOrderController(LibraryOrderService libraryOrderService) {
        this.libraryOrderService = libraryOrderService;
    }

    @PostMapping
    public ResponseEntity<LibraryOrder> createOrder(@RequestBody LibraryOrder order) {
        LibraryOrder createdOrder = libraryOrderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}