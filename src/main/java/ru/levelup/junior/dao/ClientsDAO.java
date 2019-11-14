package ru.levelup.junior.dao;

import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import javax.persistence.EntityManager;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class ClientsDAO {
    private final EntityManager manager;

    @Autowired
    public ClientsDAO(EntityManager manager){
        this.manager = manager;
    }

    public void create(Client client) {
        manager.persist(client);
    }

    public Client findByLogin(String login){
        return manager.createQuery("FROM Client WHERE login = :p", Client.class)
                .setParameter("p", login)
                .getSingleResult();

    }

    public Client findByLoginAndPassword(String login, String password){
        return manager.createQuery("FROM Client WHERE login = :l AND password = :p", Client.class)
                .setParameter("l", login)
                .setParameter("p", password)
                .getSingleResult();
    }

    public Client findByPassportNumber(long passportNumber){
        return manager.createQuery("FROM Client WHERE passportNumber = :p", Client.class)
                .setParameter("p", passportNumber)
                .getSingleResult();
    }

    public Client findByPhoneNumber(long phoneNumber) {
        Contract contract =  manager.createQuery("FROM Contract WHERE phoneNumber = :p", Contract.class)
                .setParameter("p", phoneNumber)
                .getSingleResult();
        return contract.getClient();
    }

     public Client findById(int clientId) {
        return   manager.createQuery("FROM Client WHERE id = :p", Client.class)
                .setParameter("p", clientId)
                .getSingleResult();
    }


}
