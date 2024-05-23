package ru.joraly.rhythmraid.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.joraly.rhythmraid.model.Playlist;
import ru.joraly.rhythmraid.repository.PlaylistRepository;
import ru.joraly.rhythmraid.service.PlaylistService;

import java.util.List;

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
                .orElseThrow(() -> new EntityNotFoundException(PLAYLIST_NOT_FOUND_RESPONSE));
    }

    @Transactional
    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    @Transactional
    public Playlist getById(Long id) {
        return playlistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PLAYLIST_NOT_FOUND_RESPONSE));
    }

    @Transactional
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }
}
