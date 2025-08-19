package com.example.library.service;

import com.example.library.entity.LibraryOrder;
import java.util.List;
import java.util.Optional;

public interface LibraryOrderService {
    LibraryOrder createOrder(LibraryOrder order);
    Optional<LibraryOrder> getOrderById(Long id);
    List<LibraryOrder> getAllOrders();
    LibraryOrder updateOrder(Long id, LibraryOrder order);
    void deleteOrder(Long id);
}