package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist getById(Long id);
    List<Playlist> getAll();
    Playlist save(Playlist playlist);
    Playlist replacePlaylist(Long id, Playlist playlist) throws Exception;
    void deleteById(Long id);
}
