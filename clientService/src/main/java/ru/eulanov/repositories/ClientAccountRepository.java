package ru.eulanov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.eulanov.domain.ClientAccount;

import java.util.Optional;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {

    @Query("select c from ClientAccount c join c.user u where u.login =:login ")
    Optional<ClientAccount> findByUserLogin(@Param("login") String login);
}
