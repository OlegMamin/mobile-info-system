package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Option;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class OptionsDAO {
    @PersistenceContext
    private EntityManager manager;

    public OptionsDAO() {
    }

    public OptionsDAO(EntityManager manager){
        this.manager = manager;
    }

    @Transactional
    public void create(Option option) {
        if (option.getCostOfConnection() < 0 || option.getMonthlyPayment() < 0) {
            throw new IllegalArgumentException(
                    "Options with negative costs is not allowed");
        }
        manager.persist(option);
    }

    public Option findByName(String name){
        return manager.createQuery("FROM Option WHERE name = :p", Option.class)
                .setParameter("p", name)
                .getSingleResult();

    }
    public List<Option> findByMonthlyPaymentInterval(double minPrice, double maxPrice){

        if (minPrice >=  maxPrice) {
            throw new IllegalArgumentException("maxPrice is lower then minPrice");
        }
        return manager.createQuery("FROM Option WHERE monthlyPayment >= :mi " +
                "AND monthlyPayment <= :ma", Option.class)
                .setParameter("mi", minPrice)
                .setParameter("ma", maxPrice)
                .getResultList();
    }
    public List<Option> findByCostOfConnectionInterval(double minPrice, double maxPrice){

        if (minPrice >=  maxPrice) {
            throw new IllegalArgumentException("maxPrice is lower then minPrice");
        }
        return manager.createQuery("FROM Option WHERE costOfConnection >= :mi " +
                "AND costOfConnection <= :ma", Option.class)
                .setParameter("mi", minPrice)
                .setParameter("ma", maxPrice)
                .getResultList();
    }
}
