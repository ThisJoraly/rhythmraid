package ru.joraly.rhythmraid.model.dto.response;

import lombok.Data;

@Data
public class AlbumResponse {
    private Long id;

    private String title;

    private String releaseDate;

    private String bucket;

    private String object;

}