package ru.joraly.rhythmraid.model.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String name;
    
    private String login;
}
