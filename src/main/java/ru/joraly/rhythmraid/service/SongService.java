package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Song save(Song song);
    Song replaceSong(Long id, Song song);
    List<Song> getAll();
    Optional<Song> getById(Long id);
    void deleteById(Long id);
}
