package ru.ist.account.dto;

import lombok.Data;
import ru.ist.account.exceptions.AccountValidation;
import ru.ist.account.model.Valute;

@Data
public class AccountUpdateDto {
    private final String title;
    private final String description;

    public AccountUpdateDto(String title, String description) {
        if (title != null && (title.length() < 3 || title.length() > 50)) {
            throw new AccountValidation("Название счёта не может быть короче 3 символов и длиннее 50");
        }

        this.title = title;

        if (description != null && (description.length() > 255)) {
            throw new AccountValidation("Описание счёта не может быть длиннее 255 символов");
        }

        this.description = description;
    }
}
