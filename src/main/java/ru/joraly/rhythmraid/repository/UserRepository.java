package ru.joraly.rhythmraid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
