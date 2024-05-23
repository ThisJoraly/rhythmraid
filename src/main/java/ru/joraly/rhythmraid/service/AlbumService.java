package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Optional<Album> getById(Long id);
    List<Album> getAll();
    Album save(Album album);
    Album replaceAlbum(Long id, Album album) throws Exception;
    void deleteById(Long id);
}
