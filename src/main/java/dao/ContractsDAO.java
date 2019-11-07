package dao;

import entities.Client;
import entities.Contract;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by otherz on 06.11.2019.
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

    public List<Contract> findByClient(Client client){
        return manager.createQuery("FROM Contract WHERE client.id = :p", Contract.class)
                .setParameter("p", client.getId())
                .getResultList();

    }

}
