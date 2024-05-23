package ru.joraly.rhythmraid.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.repository.AuthorRepository;
import ru.joraly.rhythmraid.service.AuthorService;
import ru.joraly.rhythmraid.util.FileComponent;

import java.util.List;
import java.util.Set;

import static ru.joraly.rhythmraid.util.Constants.AUTHOR_NOT_FOUND_RESPONSE;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileComponent fileComponent;

    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AUTHOR_NOT_FOUND_RESPONSE));
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public Set<Song> getSongsByAuthorId(long id) {
        return authorRepository.findSongsByAuthorId(id);
    }

    @Transactional
    public Set<Album> getAlbumsByAuthorId(long id) {
        return authorRepository.findAlbumsByAuthorId(id);
    }

    @Transactional
    public Author replaceAuthor(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setCountry(author.getCountry());
                    existingAuthor.setAlbumAuthors(author.getAlbumAuthors());
                    existingAuthor.setSongAuthors(author.getSongAuthors());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new EntityNotFoundException(AUTHOR_NOT_FOUND_RESPONSE));
    }


    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
