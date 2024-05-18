package ru.joraly.rhythmraid.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @NotNull
    Optional<Album> findById(@NotNull Long id);

    @Query("SELECT sa.song FROM SongAlbum sa WHERE sa.album.id = :albumId")
    Set<Song> findSongsByAlbumId(@Param("albumId") Long albumId);
}
