package com.example.library.mapper;

import com.example.library.dto.GenreDto;
import com.example.library.dto.GenreRequestDto;
import com.example.library.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);

    Genre toEntity(GenreRequestDto genreRequestDto);
}