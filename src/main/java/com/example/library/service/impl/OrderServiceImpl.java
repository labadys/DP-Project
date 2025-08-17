package com.example.library.service.impl;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;
import com.example.library.entity.LibraryOrder;
import com.example.library.exception.OrderNotFoundException;
import com.example.library.mapper.OrderMapper;
import com.example.library.repository.OrderRepository;
import com.example.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto placeOrder(OrderRequestDto orderRequest) {
        // Реализация создания заказа из OrderRequestDto
        LibraryOrder libraryOrder = orderMapper.requestToEntity(orderRequest);
        LibraryOrder savedLibraryOrder = orderRepository.save(libraryOrder);
        return orderMapper.toDto(savedLibraryOrder);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        LibraryOrder libraryOrder = orderMapper.toEntity(orderDto);
        LibraryOrder savedLibraryOrder = orderRepository.save(libraryOrder);
        return orderMapper.toDto(savedLibraryOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        LibraryOrder libraryOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.toDto(libraryOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        LibraryOrder existingLibraryOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderMapper.updateOrderFromDto(orderDto, existingLibraryOrder);
        LibraryOrder updatedLibraryOrder = orderRepository.save(existingLibraryOrder);
        return orderMapper.toDto(updatedLibraryOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
    }
}