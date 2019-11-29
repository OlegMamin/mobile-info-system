package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.dao.*;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


/**
 * Created by otherz on 11.11.2019.
 */
@Component
public class StartupListener {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private TariffsDAO tariffsDAO;

    @Autowired
    private OptionsDAO optionsDAO;

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        Client firstClient;
        Client secondClient;
        Client withoutContractClient;
        Client admin;
        Tariff tariffLow;
        Tariff tariffHigh;
        Tariff tariffMedium;
        Contract contract1;
        Contract contract2;
        Contract contract3;
        Contract contract4;
        Contract contract5;
        Contract contract6;
        Option option1;
        Option option2;
        Option option3;

        try {
            firstClient = clientsRepository.findByLogin("test");
            secondClient = clientsRepository.findByLogin("second");
            withoutContractClient = clientsRepository.findByLogin("hommy");
            admin = clientsRepository.findByLogin("admin");
            tariffLow = tariffsDAO.findByName("tariffLow");
            tariffMedium = tariffsDAO.findByName("tariffMedium");
            tariffHigh = tariffsDAO.findByName("tariffHigh");
            contract1 = contractsRepository.findByPhoneNumber("7557755");
            contract2 = contractsRepository.findByPhoneNumber("1112233");
            contract3 = contractsRepository.findByPhoneNumber("2322212");
            contract4 = contractsRepository.findByPhoneNumber("4666666");
            contract5 = contractsRepository.findByPhoneNumber("5555555");
            contract6 = contractsRepository.findByPhoneNumber("7777777");
            option1 = optionsDAO.findByName("option1");
            option2 = optionsDAO.findByName("option2");
            option3 = optionsDAO.findByName("option3");
        } catch (NoResultException notFound) {
            firstClient = new Client("John", "Terry"
                    , "1234564145", "test", "1234");
            secondClient = new Client("Frank", "Lampard"
                    , "1005323232", "second", "4567");
            withoutContractClient = new Client("Homer", "Simpson"
                    , "123456", "hommy", "1234");

            admin = new Client("Admin", "Smith"
                    , "123098", "admin", "1111");
            admin.setAdmin(true);

            tariffLow = new Tariff("tariffLow", 100);
            tariffHigh = new Tariff("tariffHigh", 300);
            tariffMedium = new Tariff("tariffMedium", 200);
            contract1 = new Contract("7557755", firstClient, tariffLow);
            contract2 = new Contract("1112233", firstClient, tariffHigh);
            contract3 = new Contract("2322212", firstClient, tariffMedium);
            contract4 = new Contract("4666666", secondClient, tariffHigh);
            contract5 = new Contract("5555555");
            contract6 = new Contract("1777777");
            option1 = new Option("option1", 10, 3);
            option2 = new Option("option2", 12, 4);
            option3 = new Option("option3", 5, 2);

            contract1.getOptions().add(option1);
            contract1.getOptions().add(option2);
            contract2.getOptions().add(option1);
            contract3.getOptions().add(option1);
            contract3.getOptions().add(option2);
            contract4.getOptions().add(option2);

            clientsRepository.save(firstClient);
            clientsRepository.save(secondClient);
            clientsRepository.save(withoutContractClient);
            clientsRepository.save(admin);
            tariffsDAO.create(tariffHigh);
            tariffsDAO.create(tariffLow);
            tariffsDAO.create(tariffMedium);
            contractsRepository.save(contract1);
            contractsRepository.save(contract2);
            contractsRepository.save(contract3);
            contractsRepository.save(contract4);
            contractsRepository.save(contract5);
            contractsRepository.save(contract6);
            optionsDAO.create(option1);
            optionsDAO.create(option2);
            optionsDAO.create(option3);
        }
    }
}