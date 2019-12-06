package ru.levelup.junior.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.repositories.OptionsRepository;
import ru.levelup.junior.repositories.TariffsRepository;

/**
 * Created by otherz on 29.11.2019.
 */
@Service
public class ContractService {

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    @Transactional
    public void removeOption(int contractId, int optionId){
        Contract contract = contractsRepository.findById(contractId).get();
        Option found = optionsRepository.findById(optionId).get();
        contract.getOptions().remove(found);
        contractsRepository.save(contract);
    }

    @Transactional
    public void connectOption(int contractId, int optionId){
        Contract contract = contractsRepository.findById(contractId).get();
        Option found = optionsRepository.findById(optionId).get();
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
    public void setClientToContract(String phoneNumber, String login){
        Contract contract = contractsRepository.findByPhoneNumber(phoneNumber);
        Client client = clientsRepository.findByLogin(login);

        contract.setClient(client);
        contractsRepository.save(contract);
    }

    @Transactional
    public void setTariffToContract(String phoneNumber, int tariffId){
        Contract contract = contractsRepository.findByPhoneNumber(phoneNumber);
        Tariff tariff = tariffsRepository.findById(tariffId).get();

        contract.setTariff(tariff);
        contractsRepository.save(contract);
    }
}
