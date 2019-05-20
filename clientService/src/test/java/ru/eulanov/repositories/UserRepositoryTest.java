package ru.eulanov.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.eulanov.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getByLogin() {
        User user = new User("login", "password");
        entityManager.persist(user);
        User expected = userRepository.findByLogin("login").orElse(null);
        assertEquals(user, expected);
    }

    @Test
    void addNewUser() {
        User user = new User("login", "password");
        userRepository.save(user);
        User expected = entityManager.find(User.class, user.getId());
        assertEquals(user, expected);
    }

    @Test
    void delete() {
        User user = new User("login", "password");
        entityManager.persist(user);
        userRepository.deleteById(user.getId());
        User expected = entityManager.find(User.class, user.getId());
        assertNull(expected);
    }
}