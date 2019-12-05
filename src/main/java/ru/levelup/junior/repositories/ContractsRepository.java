package ru.levelup.junior.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
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
@RepositoryRestResource(collectionResourceRel = "contracts", path = "contracts")
public interface ContractsRepository extends PagingAndSortingRepository<Contract, Integer> {
    Contract findByPhoneNumber(String phoneNumber);

    List<Contract> findByClient(Client client);

    List<Contract> findByClientOrderByPhoneNumber(Client client);

    @Query("FROM Contract WHERE client = null ORDER BY phoneNumber")
    List<Contract> findContractsToChose();

    @Query("SELECT a FROM Contract a WHERE a.client = null")
    Page<Contract> findContractsToChosePagination(
//            Sort sort,
            Pageable pageable
    );
}
