package ru.joraly.rhythmraid.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.mapper.PlaylistMapper;
import ru.joraly.rhythmraid.model.Playlist;
import ru.joraly.rhythmraid.model.dto.request.PlaylistRequest;
import ru.joraly.rhythmraid.model.dto.response.PlaylistResponse;
import ru.joraly.rhythmraid.service.impl.PlaylistServiceImpl;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/playlists")
@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistServiceImpl playlistService;

    private final PlaylistMapper playlistMapper;

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists() {
        return new ResponseEntity<>(playlistService
                .getAll()
                .stream()
                .map(playlistMapper::objectToResponse)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistService.getById(id);
        return new ResponseEntity<>(playlistMapper.objectToResponse(playlist), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        Playlist createdPlaylist = playlistService.save(playlistMapper.requestToObject(playlistRequest));
        return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistResponse> replacePlaylist(@PathVariable Long id, PlaylistRequest playlistRequest) {
        Playlist playlist = playlistService.replacePlaylist(id, playlistMapper.requestToObject(playlistRequest));
        PlaylistResponse playlistResponse = playlistMapper.objectToResponse(playlist);
        return new ResponseEntity<>(playlistResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
