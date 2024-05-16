package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.joraly.rhythmraid.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
