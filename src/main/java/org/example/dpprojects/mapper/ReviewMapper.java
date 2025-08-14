package org.example.dpprojects.mapper;

import org.example.dpprojects.dto.ReviewDto;
import org.example.dpprojects.dto.ReviewRequestDto;
import org.example.dpprojects.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "id", ignore = true)
    Review toEntity(ReviewRequestDto dto);

    ReviewDto toDto(Review entity);
}