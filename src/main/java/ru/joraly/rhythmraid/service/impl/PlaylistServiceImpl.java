package ru.joraly.rhythmraid.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.joraly.rhythmraid.model.Playlist;
import ru.joraly.rhythmraid.repository.PlaylistRepository;
import ru.joraly.rhythmraid.service.PlaylistService;

import java.util.List;
import java.util.Optional;

import static ru.joraly.rhythmraid.util.Constants.PLAYLIST_NOT_FOUND_RESPONSE;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;


    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }


    @Transactional
    public Playlist replacePlaylist(Long id, Playlist playlist) {
        return playlistRepository.findById(id)
                .map(existingPlaylist -> {
                    existingPlaylist.setName(playlist.getName());
                    existingPlaylist.setPosition(playlist.getPosition());
                    existingPlaylist.setSongPlaylists(playlist.getSongPlaylists());
                    return playlistRepository.save(existingPlaylist);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLAYLIST_NOT_FOUND_RESPONSE));
    }

    @Transactional
    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    @Transactional
    public Optional<Playlist> getById(Long id) {
        return playlistRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }
}
