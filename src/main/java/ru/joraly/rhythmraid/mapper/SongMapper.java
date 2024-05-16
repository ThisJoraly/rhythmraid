package ru.joraly.rhythmraid.mapper;

import org.mapstruct.Mapper;
import ru.joraly.rhythmraid.model.Album;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.model.dto.response.AlbumResponse;
import ru.joraly.rhythmraid.model.dto.request.SongRequest;
import ru.joraly.rhythmraid.model.dto.response.SongResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song requestToObject(SongRequest songRequest);
    SongResponse objectToResponse(Song song);
}

