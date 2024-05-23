package ru.joraly.rhythmraid.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.mapper.AlbumMapperImpl;
import ru.joraly.rhythmraid.mapper.AuthorMapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.dto.request.AuthorRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.response.AuthorResponse;
import ru.joraly.rhythmraid.service.impl.AuthorServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/authors")
@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorServiceImpl authorService;

    private final AuthorMapper authorMapper;
    private final AlbumMapperImpl albumMapperImpl;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return new ResponseEntity<>(authorService
                .getAll()
                .stream()
                .map(authorMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<Set<AlbumResponse>> getAlbumsByAuthorId(@PathVariable Long id) {
        Set<Album> albums = authorService.getAlbumsByAuthorId(id);
        return new ResponseEntity<>(albums.stream().map(albumMapperImpl::objectToResponse).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getById(id);
        return new ResponseEntity<>(authorMapper.objectToResponse(author), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) {
        Author createdAuthor = authorService.save(authorMapper.requestToObject(authorRequest));
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> replaceAuthor(@PathVariable Long id, AuthorRequest authorRequest) {
        Author author = authorService.replaceAuthor(id, authorMapper.requestToObject(authorRequest));
        AuthorResponse authorResponse = authorMapper.objectToResponse(author);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
