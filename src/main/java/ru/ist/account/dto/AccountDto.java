package ru.ist.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.account.model.Valute;

@Data
@AllArgsConstructor
public class AccountDto {
    private final Long id;
    private final String title;
    private final String description;
    private final Valute valute;
    private final double amount;
}
