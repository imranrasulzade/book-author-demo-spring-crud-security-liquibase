package com.matrix.springpracticeapp.controller;

import com.matrix.springpracticeapp.dto.AuthorDto;
import com.matrix.springpracticeapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        authorService.createAuthor(authorDto);
        return authorDto;
    }

    @GetMapping("readByID")
    public AuthorDto getAuthorById(@RequestParam Long authorId) {
        return authorService.findAuthor(authorId);
    }

    @GetMapping("readAllAuthors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PutMapping("update")
    public AuthorDto updateAuthor(@RequestParam Long id, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
        return HttpStatus.NO_CONTENT.name();
    }
}
