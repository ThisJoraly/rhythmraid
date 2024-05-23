package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.Song;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT sa.song FROM SongAuthor sa WHERE sa.author.id = :authorId")
    Set<Song> findSongsByAuthorId(@Param("authorId") Long authorId);

    @Query("SELECT sa.album FROM AlbumAuthor sa WHERE sa.author.id = :authorId")
    Set<Album> findAlbumsByAuthorId(@Param("authorId") Long authorId);
}
