package ru.joraly.rhythmraid.service;

import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.Song;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Author getById(Long id);
    List<Author> getAll();
    Set<Song> getSongsByAuthorId(long id);
    Set<Album> getAlbumsByAuthorId(long id);
    Author save(Author author);
    Author replaceAuthor(Long id, Author author) throws Exception;
    void deleteById(Long id);
}
