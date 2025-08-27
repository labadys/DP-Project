package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.entity.Book;
import com.example.library.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "author")
    @Mapping(source = "publisher.name", target = "publisher")
    @Mapping(source = "genres", target = "genre") // Будет нужен кастомный метод
    BookDto toDto(Book book);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookDto bookDto);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookRequestDto bookRequestDto);

    // Кастомный метод для преобразования коллекции жанров в строку
    default String mapGenresToString(List<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return null;
        }
        // Берем первый жанр или объединяем все
        return genres.get(0).getName();
    }
}