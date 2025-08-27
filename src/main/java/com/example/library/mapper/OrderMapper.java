package com.example.library.mapper;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;
import com.example.library.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderDto orderDto);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderRequestDto orderRequestDto);
}