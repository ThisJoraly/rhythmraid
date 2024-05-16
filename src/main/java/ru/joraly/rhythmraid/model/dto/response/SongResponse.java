package ru.joraly.rhythmraid.model.dto.response;

import lombok.Data;

@Data
public class SongResponse {
    private Long id;

    private String title;

    private String genre;

    private Integer duration;

}
