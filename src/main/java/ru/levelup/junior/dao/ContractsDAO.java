package ru.levelup.junior.dao;

import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class ContractsDAO {
    private final EntityManager manager;

    @Autowired
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

    public List<Contract> findByClient(Client client){
        return manager.createQuery("FROM Contract WHERE client.id = :p", Contract.class)
                .setParameter("p", client.getId())
                .getResultList();

    }



}
