package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;

import java.util.Set;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT s FROM Song s WHERE s.album.id = :albumId")
    Set<Song> findSongsByAlbumId(@Param("albumId") Long albumId);

    @Query("SELECT a.title FROM Album a WHERE a.id = :albumId")
    String getTitleById(@Param("albumId") Long albumId);

    @Query("SELECT aa.author FROM AlbumAuthor aa WHERE aa.album.id = :albumId")
    Set<String> findAuthorsByAlbumId(@Param("albumId") Long albumId);
}
