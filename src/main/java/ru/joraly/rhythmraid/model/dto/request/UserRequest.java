package ru.joraly.rhythmraid.model.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String login;
    private String password;
}
