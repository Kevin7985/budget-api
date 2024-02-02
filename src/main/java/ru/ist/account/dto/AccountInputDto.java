package ru.ist.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.account.model.Valute;

@Data
@AllArgsConstructor
public class AccountInputDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private final String title;

    private final String description;
    private final Valute valute;
    private final double amount;
}
