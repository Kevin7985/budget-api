package ru.ist.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.account.model.Valute;
import ru.ist.user.dto.UserDto;

@Data
@AllArgsConstructor
public class AccountDto {
    private final Long id;
    private final UserDto user;
    private final String title;
    private final String description;
    private final Valute valute;
    private final double amount;
}
