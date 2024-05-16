package ru.joraly.rhythmraid.model.dto.response;
import lombok.Data;

import java.util.List;

@Data
public class AlbumResponse {
    private Long id;

    private String title;

    private String releaseDate;

    private List<SongResponse> songs;
}