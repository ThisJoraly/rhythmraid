package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;

import java.util.List;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
