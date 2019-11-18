package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class ContractsDAO {
    @PersistenceContext
    private EntityManager manager;

    public ContractsDAO() {
    }

    public ContractsDAO(EntityManager manager){
        this.manager = manager;
    }

    @Transactional
    public void create(Contract contract) {
        if (contract.getClient() == null) {
            throw new IllegalArgumentException(
                    "Contract without client is not valid");
        }
        if (contract.getTariff() == null) {
            throw new IllegalArgumentException(
                    "Contract without tariff is not valid");
        }
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
