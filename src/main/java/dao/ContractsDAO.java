package dao;

import entities.Client;
import entities.Contract;

import javax.persistence.EntityManager;

/**
 * Created by AHYC on 06.11.2019.
 */
public class ContractsDAO {
    private final EntityManager manager;

    public ContractsDAO(EntityManager manager){
        this.manager = manager;
    }

    public void create(Contract contract) {
        manager.persist(contract);
    }

    public Contract findByPhoneNumber(long phoneNumber){
        return manager.createQuery("FROM Contract WHERE phoneNumber = :p", Contract.class)
                .setParameter("p", phoneNumber)
                .getSingleResult();

    }

}
