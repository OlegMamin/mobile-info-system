package ru.levelup.junior.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by otherz on 29.11.2019.
 */
@Service
public class ContractService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Transactional
    public void removeOption(int contractId, int optionId){
        Contract contract = contractsRepository.findById(contractId).get();
        Option found = manager.find(Option.class, optionId);
        contract.getOptions().remove(found);
        contractsRepository.save(contract);
    }

    @Transactional
    public void connectOption(int contractId, int optionId){
        Contract contract = contractsRepository.findById(contractId).get();
        Option found = manager.find(Option.class, optionId);
        contract.getOptions().add(found);
        contractsRepository.save(contract);
    }

    @Transactional
    public void blockTariff(int contractId){
        Contract contract = contractsRepository.findById(contractId).get();
        contract.setTariff(null);
        contractsRepository.save(contract);
    }

    @Transactional
    public void terminateContract(int contractId){
        Contract contract = contractsRepository.findById(contractId).get();
        contract.setClient(null);
        contractsRepository.save(contract);
    }

    @Transactional
    public void setClientToContract(String phoneNumber, int clientId){
        Contract contract = contractsRepository.findByPhoneNumber(phoneNumber);
        Client client = clientsRepository.findById(clientId).get();

        contract.setClient(client);
        contractsRepository.save(contract);
    }

    @Transactional
    public void setTariffToContract(String phoneNumber, int tariffId){
        Contract contract = contractsRepository.findByPhoneNumber(phoneNumber);
        Tariff tariff = manager.find(Tariff.class, tariffId);

        contract.setTariff(tariff);
        contractsRepository.save(contract);
    }
}
