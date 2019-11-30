package ru.levelup.junior.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.entities.Contract;

import java.util.List;

/**
 * Created by otherz on 14.11.2019.
 */
@Service
public class DashboardService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    public List<Contract> getContracts(int clientId) {
        return clientsRepository.findById(clientId).map(client -> contractsRepository.findByClient(client)).get();
    }
}
