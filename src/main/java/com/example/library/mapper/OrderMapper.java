package com.example.library.mapper;

import com.example.library.dto.OrderDto;
import com.example.library.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
    void updateOrderFromDto(OrderDto orderDto, @MappingTarget Order order);
}
