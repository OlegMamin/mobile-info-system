package dao;

import entities.Option;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by AHYC on 06.11.2019.
 */
public class OptionsDAO {
    private final EntityManager manager;

    public OptionsDAO(EntityManager manager){
        this.manager = manager;
    }

    public void create(Option option) {
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
