package ru.levelup.junior.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by otherz on 29.11.2019.
 */
@Service
public class ClientService {

    @Autowired
    private ContractsRepository contractsRepository;

    public Client findByPhoneNumber(String phoneNumber) {
        return contractsRepository.findByPhoneNumber(phoneNumber).getClient();
    }
}
