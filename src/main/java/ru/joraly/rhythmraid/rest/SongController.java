package ru.joraly.rhythmraid.rest;

import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.mapper.AlbumMapper;
import ru.joraly.rhythmraid.mapper.SongMapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.model.dto.request.SongRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.response.SongResponse;
import ru.joraly.rhythmraid.service.impl.SongServiceImpl;
import ru.joraly.rhythmraid.util.FileComponent;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    private final AlbumMapper albumMapperImpl;
    private final FileComponent fileComponent;
    private final String bucketName;

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs() {
        return new ResponseEntity<>(songService
                .getAll()
                .stream()
                .map(songMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/album")
    public ResponseEntity<AlbumResponse> getAlbumBySongId(@PathVariable Long id) {
        Optional<Album> albumOptional = songService.getAlbumBySongId(id);

        if (albumOptional.isPresent()) {
            AlbumResponse albumResponse = albumMapperImpl.objectToResponse(albumOptional.get());
            return new ResponseEntity<>(albumResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<SongResponse> replaceSong(@PathVariable Long id, @RequestBody SongRequest songRequest) {
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
