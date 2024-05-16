package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.joraly.rhythmraid.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
