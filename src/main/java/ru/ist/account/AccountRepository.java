package ru.ist.account;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ist.account.model.Account;
import ru.ist.account.model.Valute;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser_IdAndValuteIn(Long userId, List<Valute> valuteList);

    List<Account> findByUser_Id(Long userId);
}
