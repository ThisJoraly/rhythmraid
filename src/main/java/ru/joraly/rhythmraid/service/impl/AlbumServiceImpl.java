package ru.joraly.rhythmraid.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.repository.AlbumRepository;
import ru.joraly.rhythmraid.service.AlbumService;
import static ru.joraly.rhythmraid.util.Constants.ALBUM_NOT_FOUND_RESPONSE;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public Optional<Album> getById(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @Transactional
    public Set<Song> getSongsByAlbumId(long id) {
        return albumRepository.findSongsByAlbumId(id);
    }

    @Transactional
    public Album replaceAlbum(Long id, Album album) {
        return albumRepository.findById(id)
                .map(existingAlbum -> {
                    existingAlbum.setTitle(album.getTitle());
                    existingAlbum.setReleaseDate(album.getReleaseDate());
                    existingAlbum.setSongAlbums(album.getSongAlbums());
                    existingAlbum.setAlbumAuthors(album.getAlbumAuthors());
                    return albumRepository.save(album);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ALBUM_NOT_FOUND_RESPONSE));
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
