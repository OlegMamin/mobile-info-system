package ru.levelup.junior.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.repositories.ContractsRepository;

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
