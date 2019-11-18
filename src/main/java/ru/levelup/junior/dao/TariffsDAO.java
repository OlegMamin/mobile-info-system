package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class TariffsDAO {
    @PersistenceContext
    private EntityManager manager;

    public TariffsDAO() {
    }

    public TariffsDAO(EntityManager manager){
        this.manager = manager;
    }

    @Transactional
    public void create(Tariff tariff) {
        if (tariff.getPrice() < 0) {
            throw new IllegalArgumentException(
                    "Tariff with negative price is not allowed");
        }
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
