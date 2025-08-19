package com.example.library.mapper;

import com.example.library.dto.OrderDto;
import com.example.library.dto.OrderRequestDto;
import com.example.library.entity.LibraryOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    /**
     * @return
     */
    default LibraryOrder toEntity() {
        return toEntity(null);
    }

    /**
     * @param orderDto
     * @return
     */
    LibraryOrder toEntity(OrderDto orderDto);

    /**
     * @param libraryOrder 
     * @return
     */
    OrderDto toDto(LibraryOrder libraryOrder);

    /**
     * @param orderDto 
     * @param libraryOrder
     */
    void updateOrderFromDto(OrderDto orderDto, @MappingTarget LibraryOrder libraryOrder);

    /**
     * @param orderRequest 
     * @return
     */
    LibraryOrder requestToEntity(OrderRequestDto orderRequest);
}
