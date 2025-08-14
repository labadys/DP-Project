package com.example.library.service;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder(OrderRequestDto orderRequest);

    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(Long id, OrderDto orderDto);
    void deleteOrder(Long id);
}
