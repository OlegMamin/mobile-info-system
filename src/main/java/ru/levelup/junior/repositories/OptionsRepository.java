package ru.levelup.junior.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Option;

import java.util.List;

/**
 * Created by otherz on 29.11.2019.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "options", path = "options")
public interface OptionsRepository extends PagingAndSortingRepository<Option, Integer> {
    Option findByName(String name);

    List<Option> findByMonthlyPaymentBetween(double minPrice, double maxPrice);

    List<Option> findByCostOfConnectionBetween(double minPrice, double maxPrice);
}
