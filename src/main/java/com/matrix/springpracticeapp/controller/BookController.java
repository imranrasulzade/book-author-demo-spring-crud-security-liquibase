package com.matrix.springpracticeapp.controller;

import com.matrix.springpracticeapp.dto.BookDto;
import com.matrix.springpracticeapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PostMapping("create")
    public BookDto create(@RequestBody BookDto dto) {
        service.create(dto);
        return dto;
    }

    @GetMapping("readAll")
    public List<BookDto> readAll() {
        return service.readAll();
    }

    @GetMapping("readById")
    public BookDto readByID(@RequestParam Long id) {
        return service.readByID(id);
    }

    @GetMapping("readByPrice")
    public List<BookDto> readByPrice(@RequestParam("minPrice") int minPrice) {
        return service.findBooksByPriceRange(minPrice);
    }

    @PutMapping("update")
    public BookDto update(@RequestBody BookDto dto, @RequestParam Long id) {
        return service.update(dto, id);
    }

    @DeleteMapping("delete")
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return HttpStatus.OK.name();
    }
}