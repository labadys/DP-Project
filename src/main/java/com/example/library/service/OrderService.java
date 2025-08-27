package com.example.library.service;

import com.example.library.dto.OrderDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDto); // Измените на OrderDto
    OrderDto updateOrder(Long id, OrderDto orderDto); // Измените на OrderDto
    void deleteOrder(Long id);
}