package ru.levelup.junior.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Tariff;

import java.util.List;

/**
 * Created by otherz on 29.11.2019.
 */
@Repository
public interface TariffsRepository extends CrudRepository<Tariff, Integer>{
    Tariff findByName(String name);

    List<Tariff> findByPriceBetween(double minPrice, double maxPrice);
}
