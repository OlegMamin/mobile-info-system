package ru.levelup.junior.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

/**
 * Created by otherz on 29.11.2019.
 */
@Repository
public interface ClientsRepository extends PagingAndSortingRepository<Client, Integer> {
    Client findByLogin(String login);

    Client findByLoginAndPassword(String login, String password);

    Client findByPassportNumber(String passportNumber);
}
