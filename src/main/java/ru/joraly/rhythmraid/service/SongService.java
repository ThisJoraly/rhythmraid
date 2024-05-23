package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Song;

import java.util.List;

public interface SongService {
    Song save(Song song);
    Song replaceSong(Long id, Song song) throws Exception;
    List<Song> getAll();
    Song getById(Long id);
    void deleteById(Long id);
}
