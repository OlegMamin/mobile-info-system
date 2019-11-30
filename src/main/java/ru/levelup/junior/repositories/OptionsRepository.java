package ru.levelup.junior.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Option;

import java.util.List;

/**
 * Created by otherz on 29.11.2019.
 */
@Repository
public interface OptionsRepository extends CrudRepository<Option, Integer> {
    Option findByName(String name);

    List<Option> findByMonthlyPaymentBetween(double minPrice, double maxPrice);

    List<Option> findByCostOfConnectionBetween(double minPrice, double maxPrice);
}
