package ru.levelup.junior.dao;

import ru.levelup.junior.entities.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class TariffsDAO {
    private final EntityManager manager;

    @Autowired
    public TariffsDAO(EntityManager manager){
        this.manager = manager;
    }

    public void create(Tariff tariff) {
        manager.persist(tariff);
    }

    public Tariff findByName(String name){
        return manager.createQuery("FROM Tariff WHERE name = :p", Tariff.class)
                .setParameter("p", name)
                .getSingleResult();

    }
    public List<Tariff> findByPriceInterval(double minPrice, double maxPrice){

        if (minPrice >=  maxPrice) {
            throw new IllegalArgumentException("maxPrice is lower then minPrice");
        }
        return manager.createQuery("FROM Tariff WHERE price >= :mi AND price <= :ma", Tariff.class)
                .setParameter("mi", minPrice)
                .setParameter("ma", maxPrice)
                .getResultList();
    }
}
