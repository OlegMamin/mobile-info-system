package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by otherz on 06.11.2019.
 */
@Repository
public class ClientsDAO {

    @PersistenceContext
    private EntityManager manager;

    public ClientsDAO() {
    }

    public ClientsDAO(EntityManager manager){
        this.manager = manager;
    }

    @Transactional
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

    public Client findByPassportNumber(String passportNumber){
        return manager.createQuery("FROM Client WHERE passportNumber = :p", Client.class)
                .setParameter("p", passportNumber)
                .getSingleResult();
    }

    public Client findByPhoneNumber(String phoneNumber) {
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
