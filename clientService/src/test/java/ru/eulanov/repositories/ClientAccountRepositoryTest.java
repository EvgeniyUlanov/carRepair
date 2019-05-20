package ru.eulanov.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.eulanov.domain.ClientAccount;
import ru.eulanov.domain.Role;
import ru.eulanov.domain.User;
import ru.eulanov.utils.RolesNames;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClientAccountRepositoryTest {

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByUserLogin() {
        Role role = entityManager.persist(new Role(RolesNames.CLIENT));
        User user = new User("login", "pas");
        user.setRole(role);
        entityManager.persist(user);
        ClientAccount account = new ClientAccount("firstName", "secondName", "tele");
        account.setUser(user);
        entityManager.persist(account);

        ClientAccount expected = clientAccountRepository.findByUserLogin("login").orElse(null);

        assertNotNull(expected);
    }
}