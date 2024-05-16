package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.joraly.rhythmraid.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(Long id);
}
