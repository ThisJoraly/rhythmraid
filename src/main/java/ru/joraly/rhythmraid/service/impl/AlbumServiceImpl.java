package ru.joraly.rhythmraid.service.impl;

import io.minio.errors.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.repository.AlbumRepository;
import ru.joraly.rhythmraid.repository.AuthorRepository;
import ru.joraly.rhythmraid.service.AlbumService;
import static ru.joraly.rhythmraid.util.Constants.ALBUM_NOT_FOUND_RESPONSE;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.joraly.rhythmraid.util.FileComponent;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final FileComponent fileComponent;
    private final AuthorRepository authorRepository;

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
                .orElseThrow(() -> new EntityNotFoundException(ALBUM_NOT_FOUND_RESPONSE));
    }

    public void uploadCover(String bucketName, InputStream inputStream, long size, long id)
            throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException,
            InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        Set<String> authors = albumRepository.findAuthorsByAlbumId(id);
        String mainAuthor = authors.stream().findFirst().orElse(null);

        String name = mainAuthor + "/" + albumRepository.getTitleById(id);
        fileComponent.uploadFile(bucketName, name, inputStream, size);

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Album by ID not found"));

        album.setObject(name);
        albumRepository.save(album);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
