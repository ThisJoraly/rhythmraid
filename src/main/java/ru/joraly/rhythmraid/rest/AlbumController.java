package ru.joraly.rhythmraid.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.mapper.AlbumMapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.dto.request.AlbumRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.service.impl.AlbumServiceImpl;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/albums")
@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumServiceImpl albumService;

    private final AlbumMapper albumMapper;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        return new ResponseEntity<>(albumService
                .getAll()
                .stream()
                .map(albumMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.getById(id);
        return album.map(value -> new ResponseEntity<>(albumMapper
                .objectToResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody AlbumRequest albumRequest) {
        Album createdAlbum = albumService.save(albumMapper.requestToObject(albumRequest));
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponse> replaceAlbum(@PathVariable Long id, AlbumRequest albumRequest) {
        Album album = albumService.replaceAlbum(id, albumMapper.requestToObject(albumRequest));
        AlbumResponse albumResponse = albumMapper.objectToResponse(album);
        return new ResponseEntity<>(albumResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
