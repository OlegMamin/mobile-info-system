package ru.levelup.junior.dao;

import org.springframework.stereotype.Service;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by otherz on 29.11.2019.
 */
@Service
public class ClientService {

    @PersistenceContext
    private EntityManager manager;

    public Client findByPhoneNumber(String phoneNumber) {
        Contract contract =  manager.createQuery("FROM Contract WHERE phoneNumber = :p", Contract.class)
                .setParameter("p", phoneNumber)
                .getSingleResult();
        return contract.getClient();
    }
}
