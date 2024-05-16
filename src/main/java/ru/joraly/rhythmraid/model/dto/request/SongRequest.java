package ru.joraly.rhythmraid.model.dto.request;

import lombok.Data;

@Data
public class SongRequest {
    private Long id;

    private String title;

    private String genre;

    private Integer duration;

    private String bucket;

    private String filePath;
}
