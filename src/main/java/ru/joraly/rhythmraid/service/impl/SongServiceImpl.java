package ru.joraly.rhythmraid.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.repository.SongRepository;
import ru.joraly.rhythmraid.service.SongService;

import static ru.joraly.rhythmraid.util.Constants.PLAYLIST_NOT_FOUND_RESPONSE;
import static ru.joraly.rhythmraid.util.Constants.SONG_NOT_FOUND_RESPONSE;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {


    private final SongRepository songRepository;


    public Song save(Song song) {
        return songRepository.save(song);
    }


    @Transactional
    public Song replaceSong(Long id, Song song) {
        return songRepository.findById(id)
                .map(existingSong -> {
                    existingSong.setTitle(song.getTitle());
                    existingSong.setGenre(song.getGenre());
                    existingSong.setDuration(song.getDuration());
                    existingSong.setBucket(song.getBucket());
                    existingSong.setObject(song.getObject());
                    return songRepository.save(existingSong);
                })
                .orElseThrow(() -> new EntityNotFoundException(SONG_NOT_FOUND_RESPONSE));
    }

    @Transactional
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Transactional
    public Song getById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PLAYLIST_NOT_FOUND_RESPONSE));
    }

    @Transactional
    public Set<Album> getAlbumsBySongId(long id) {
        return songRepository.findAlbumsBySongId(id);
    }


    @Transactional
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
