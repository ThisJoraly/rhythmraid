package ru.joraly.rhythmraid.mapper;

import org.mapstruct.Mapper;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.model.User;
import ru.joraly.rhythmraid.model.dto.request.UserRequest;
import ru.joraly.rhythmraid.model.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToObject(UserRequest userRequest);
    UserResponse objectToResponse(User user);
}

