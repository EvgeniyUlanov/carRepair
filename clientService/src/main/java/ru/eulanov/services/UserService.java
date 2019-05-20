package ru.eulanov.services;

import ru.eulanov.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByLogin(String login);

    void addNewUser(User user);

    void delete(Long id);
}
