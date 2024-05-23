package ru.joraly.rhythmraid.mapper;

import org.mapstruct.Mapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Author;
import ru.joraly.rhythmraid.model.dto.request.AlbumRequest;
import ru.joraly.rhythmraid.model.dto.request.AuthorRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.response.AuthorResponse;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author requestToObject(AuthorRequest authorRequest);
    AuthorResponse objectToResponse(Author author);
}

