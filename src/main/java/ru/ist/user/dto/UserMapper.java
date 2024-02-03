package ru.ist.user.dto;

import org.springframework.stereotype.Component;
import ru.ist.user.model.User;

@Component
public class UserMapper {
    public User toUser(UserInputDto userInputDto) {
        return new User(
                null,
                userInputDto.getLogin()
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin()
        );
    }
}
