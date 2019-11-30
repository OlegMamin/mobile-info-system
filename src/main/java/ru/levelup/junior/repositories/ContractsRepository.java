package ru.levelup.junior.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import java.util.List;

/**
 * Created by otherz on 29.11.2019.
 */
@Repository
public interface ContractsRepository extends CrudRepository<Contract, Integer>{
    Contract findByPhoneNumber(String phoneNumber);

    List<Contract> findByClient(Client client);

    @Query("FROM Contract WHERE client = null ORDER BY phoneNumber")
    List<Contract> findContractsToChose();
}
