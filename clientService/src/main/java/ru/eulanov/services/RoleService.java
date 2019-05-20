package ru.eulanov.services;

import ru.eulanov.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> getByName(String name);

    void addRole(Role role);

    void delete(Long id);

    List<Role> getAll();
}
