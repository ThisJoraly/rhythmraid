package ru.joraly.rhythmraid.model.dto.request;

import lombok.Data;

@Data
public class AlbumRequest {
    private Long id;

    private String title;

    private String releaseDate;
}
