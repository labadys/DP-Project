package com.example.library.mapper;

import com.example.library.dto.PublisherDto;
import com.example.library.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDto toDto(Publisher publisher);

    /**
     * @return
     */
    default Publisher toEntity() {
        return toEntity(null);
    }

    /**
     * @param publisherDto
     * @return
     */
    Publisher toEntity(PublisherDto publisherDto);
}
