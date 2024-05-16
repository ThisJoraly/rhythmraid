package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    Optional<Playlist> getById(Long id);
    List<Playlist> getAll();
    Playlist save(Playlist playlist);
    Playlist replacePlaylist(Long id, Playlist playlist);
    void deleteById(Long id);
}
