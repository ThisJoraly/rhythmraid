package ru.joraly.rhythmraid.mapper;

import org.mapstruct.Mapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.dto.request.AlbumRequest;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    Album requestToObject(AlbumRequest albumRequest);
    AlbumResponse objectToResponse(Album album);
}
