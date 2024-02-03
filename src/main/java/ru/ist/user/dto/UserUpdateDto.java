package ru.ist.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ist.user.exceptions.UserValidation;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    private String login;

    public UserUpdateDto(String login) {
        if (login != null && (login.length() < 5 || login.length() > 255)) {
            throw new UserValidation("Логин должен содержать не менее 5 символов и не более 255");
        }

        this.login = login;
    }
}
