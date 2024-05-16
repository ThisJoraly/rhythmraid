package ru.joraly.rhythmraid.mapper;

import org.mapstruct.Mapper;
import ru.joraly.rhythmraid.model.Playlist;
import ru.joraly.rhythmraid.model.dto.request.PlaylistRequest;
import ru.joraly.rhythmraid.model.dto.response.PlaylistResponse;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist requestToObject(PlaylistRequest playlistRequest);
    PlaylistResponse objectToResponse(Playlist playlist);
}