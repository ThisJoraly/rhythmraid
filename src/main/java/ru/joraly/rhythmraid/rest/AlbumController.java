package ru.joraly.rhythmraid.rest;

import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.joraly.rhythmraid.configuration.MinioConfiguration;
import ru.joraly.rhythmraid.mapper.AlbumMapper;
import ru.joraly.rhythmraid.mapper.SongMapperImpl;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.model.dto.request.AlbumRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.response.SongResponse;
import ru.joraly.rhythmraid.service.impl.AlbumServiceImpl;
import ru.joraly.rhythmraid.util.Constants;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/albums")
@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumServiceImpl albumService;
    private final MinioConfiguration minioConfiguration;
    private final AlbumMapper albumMapper;
    private final SongMapperImpl songMapperImpl;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        return new ResponseEntity<>(albumService
                .getAll()
                .stream()
                .map(albumMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Set<SongResponse>> getSongsByAlbumId(@PathVariable Long id) {
        Set<Song> songs = albumService.getSongsByAlbumId(id);
        return new ResponseEntity<>(songs.stream().map(songMapperImpl::objectToResponse).collect(Collectors.toSet()), HttpStatus.OK);
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

    @PostMapping("/{id}/cover")
    public ResponseEntity<String> uploadCover(@RequestPart(value = "file") MultipartFile file, @PathVariable long id) throws IOException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException,
            InvalidKeyException, ErrorResponseException,
            XmlParserException, InternalException, ServerException {
        if(!(Arrays.asList(Constants.IMAGE_TYPES).contains(file.getContentType()))) {
            return new ResponseEntity<>("File is not image", HttpStatus.BAD_REQUEST);
        }
        String bucketName = minioConfiguration.bucketName();
        albumService.uploadCover(bucketName, file.getInputStream(), file.getSize(), id);
        return new ResponseEntity<>("Cover uploaded successfully", HttpStatus.OK);
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
