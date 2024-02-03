package ru.ist.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;
import ru.ist.user.dto.UserDto;
import ru.ist.user.dto.UserInputDto;
import ru.ist.user.dto.UserUpdateDto;
import ru.ist.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MapperService mapperService;
    private final ValidationService validationService;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserInputDto userInputDto) {
        User user = mapperService.toUser(userInputDto);

        log.info("Создан новый пользователь: " + user);
        return mapperService.toUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(mapperService::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = validationService.validateUser(id);

        log.info("Получение информации о пользователе: " + user);
        return mapperService.toUserDto(user);
    }

    @Override
    public UserDto updateUserById(Long id, UserUpdateDto userUpdateDto) {
        User user = validationService.validateUser(id);

        user.setLogin(userUpdateDto.getLogin() == null ? user.getLogin() : userUpdateDto.getLogin());

        log.info("Обновлена информация о пользователе: " + user);
        return mapperService.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        validationService.validateUser(id);

        log.info("Пользователь с id = " + id + " успешно удалён");
        userRepository.deleteById(id);
    }
}
