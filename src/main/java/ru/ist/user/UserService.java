package ru.ist.user;

import ru.ist.user.dto.UserDto;
import ru.ist.user.dto.UserInputDto;
import ru.ist.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserInputDto userInputDto);

    List<UserDto> getUsers();

    UserDto getUserById(Long id);

    UserDto updateUserById(Long id, UserUpdateDto userUpdateDto);

    void deleteUserById(Long id);
}
