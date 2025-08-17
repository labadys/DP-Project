package com.example.library.mapper;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;
import com.example.library.entity.LibraryOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    /**
     * @param orderDto
     * @return
     */
    LibraryOrder toEntity(OrderDto orderDto);
    OrderDto toDto(LibraryOrder libraryOrder);
    void updateOrderFromDto(OrderDto orderDto, @MappingTarget LibraryOrder libraryOrder);

    LibraryOrder requestToEntity(OrderRequestDto orderRequest);
}
