package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;

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

        manager.persist(contract);
    }

    public Contract findByPhoneNumber(String phoneNumber){
        return manager.createQuery("FROM Contract WHERE phoneNumber = :p", Contract.class)
                .setParameter("p", phoneNumber)
                .getSingleResult();

    }

    public List<Contract> findByClient(Client client){
        return manager.createQuery("FROM Contract WHERE client.id = :p", Contract.class)
                .setParameter("p", client.getId())
                .getResultList();

    }

    public Contract findById(int contractId){
        return manager.createQuery("FROM Contract WHERE id = :p", Contract.class)
                .setParameter("p", contractId)
                .getSingleResult();

    }
    @Transactional
    public void removeOption(int contractId, int optionId){
        Contract contract = findById(contractId);
        Option found = manager.find(Option.class, optionId);
        contract.getOptions().remove(found);
        manager.persist(contract);
    }

    @Transactional
    public void blockTariff(int contractId){
        Contract contract = findById(contractId);
        contract.setTariff(null);
        manager.persist(contract);
    }
    @Transactional
    public void terminateContract(int contractId){
        Contract contract = findById(contractId);
        contract.setClient(null);
        manager.persist(contract);
    }
}
