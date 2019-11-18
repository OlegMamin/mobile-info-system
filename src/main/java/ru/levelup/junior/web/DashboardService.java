package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;

import java.util.List;

/**
 * Created by otherz on 14.11.2019.
 */
@Service
public class DashboardService {

    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private ContractsDAO contractsDAO;

    public List<Contract> getContracts(int clientId) {
        Client found = clientsDAO.findById(clientId);
        return contractsDAO.findByClient(found);
    }
}
