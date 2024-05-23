package ru.joraly.rhythmraid.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.mapper.AlbumMapperImpl;
import ru.joraly.rhythmraid.mapper.SongMapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.model.dto.request.SongRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.response.SongResponse;
import ru.joraly.rhythmraid.service.impl.SongServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/songs")
@RestController
@RequiredArgsConstructor
public class SongController {
    private final SongServiceImpl songService;

    private final SongMapper songMapper;
    private final AlbumMapperImpl albumMapperImpl;

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs() {
        return new ResponseEntity<>(songService
                .getAll()
                .stream()
                .map(songMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<Set<AlbumResponse>> getAlbumsBySongId(@PathVariable Long id) {
        Set<Album> albums = songService.getAlbumsBySongId(id);
        return new ResponseEntity<>(albums.stream().map(albumMapperImpl::objectToResponse).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable Long id) {
        Song song = songService.getById(id);

        return new ResponseEntity<>(songMapper.objectToResponse(song), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody SongRequest songRequest) {
        Song createdSong = songService.save(songMapper.requestToObject(songRequest));
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongResponse> replaceSong(@PathVariable Long id, SongRequest songRequest) {
        Song song = songService.replaceSong(id, songMapper.requestToObject(songRequest));
        SongResponse songResponse = songMapper.objectToResponse(song);
        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
