package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.entity.Book;
import com.example.library.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book toEntity(BookRequestDto request);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "genreIds", source = "genres", qualifiedByName = "mapGenresToIds")
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "genres", ignore = true)
    void updateEntity(BookRequestDto request, @MappingTarget Book book);

    @Named("mapGenresToIds")
    default Set<Long> mapGenresToIds(Set<Genre> genres) {
        if (genres == null) {
            return null;
        }
        return genres.stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());
    }
}