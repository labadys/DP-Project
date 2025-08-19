package com.example.library.service.impl;

import com.example.library.entity.LibraryOrder;
import com.example.library.repository.OrderRepository;
import com.example.library.service.LibraryOrderService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryOrderServiceImpl implements LibraryOrderService {

    private final OrderRepository repository;

    public LibraryOrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public LibraryOrder createOrder(LibraryOrder order) {
        return repository.save(order);
    }

    @Override
    public Optional<LibraryOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<LibraryOrder> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public LibraryOrder updateOrder(Long id, LibraryOrder order) {
        order.setId(id);
        return repository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}