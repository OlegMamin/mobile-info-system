package ru.levelup.junior.repositories;

import org.springframework.data.jpa.repository.Query;
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

    @Query("FROM Tariff ORDER BY price")
    List<Tariff> findAllOrderByPrice();

    List<Tariff> findByPriceBetween(double minPrice, double maxPrice);
}
