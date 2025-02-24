package ru.joraly.rhythmraid.model.dto.response;

import lombok.Data;
import ru.joraly.rhythmraid.model.Album;

@Data
public class SongResponse {
    private Long id;

    private String title;

    private String genre;

    private Integer duration;

    private String bucket;

    private String object;

    private Album album;

}
