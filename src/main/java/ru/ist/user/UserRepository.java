package ru.ist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ist.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
