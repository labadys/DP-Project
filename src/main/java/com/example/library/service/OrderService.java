package com.example.library.service;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;

public interface OrderService {
    OrderDto placeOrder(OrderRequestDto orderRequest);
}
