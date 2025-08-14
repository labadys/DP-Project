package com.example.library.mapper;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;
import com.example.library.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderRequestDto orderRequestDto);

    OrderDto toDto(Order order);
}
