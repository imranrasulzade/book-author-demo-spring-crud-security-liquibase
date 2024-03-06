package com.matrix.springpracticeapp.mapper;

import com.matrix.springpracticeapp.dto.BookDto;
import com.matrix.springpracticeapp.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    BookDto bookDaoToDto(Book book);

    @Mapping(source = "authorId", target = "author.id")
    Book bookDtoToDao(BookDto bookDto);

    List<BookDto> convertDAOListToDTOList(List<Book> daoList);

    List<Book> convertDTOListToDAOList(List<BookDto> dtoList);
}