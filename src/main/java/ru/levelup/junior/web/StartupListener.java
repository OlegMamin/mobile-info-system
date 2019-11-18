package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.OptionsDAO;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.OptionsDAO;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Random;


/**
 * Created by otherz on 11.11.2019.
 */
@Component
public class StartupListener {

    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private ContractsDAO contractsDAO;

    @Autowired
    private TariffsDAO tariffsDAO;

    @Autowired
    private OptionsDAO optionsDAO;

    @Autowired
    private EntityManager manager;

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        Client firstClient;
        Client secondClient;
        Tariff tariffLow;
        Tariff tariffHigh;
        Tariff tariffMedium;
        Contract contract1;
        Contract contract2;
        Contract contract3;
        Contract contract4;
        Option option1;
        Option option2;

        manager.getTransaction().begin();
        try {
            firstClient = clientsDAO.findByLogin("test");
            secondClient = clientsDAO.findByLogin("second");
        } catch (NoResultException notFound) {
            firstClient = new Client("John", "Terry", 1234564145, "test", "1234");
            secondClient = new Client("Frank", "Lampard", 1005323232, "frog", "4567");
            tariffLow = new Tariff("tariffLow", 100);
            tariffHigh = new Tariff("tariffHigh", 300);
            tariffMedium = new Tariff("tariffMedium", 200);
            contract1 = new Contract(7557755, firstClient, tariffLow);
            contract2 = new Contract(1112233, firstClient, tariffHigh);
            contract3 = new Contract(2322212, firstClient, tariffMedium);
            contract4 = new Contract(4666666, secondClient, tariffHigh);
            option1 = new Option("testDao1", 10, 3);
            option2 = new Option("testDao2", 12, 4);

            contract1.getOptions().add(option1);
            contract1.getOptions().add(option2);
            contract2.getOptions().add(option1);
            contract3.getOptions().add(option1);
            contract3.getOptions().add(option2);
            contract4.getOptions().add(option2);

            clientsDAO.create(firstClient);
            clientsDAO.create(secondClient);
            tariffsDAO.create(tariffHigh);
            tariffsDAO.create(tariffLow);
            tariffsDAO.create(tariffMedium);
            contractsDAO.create(contract1);
            contractsDAO.create(contract2);
            contractsDAO.create(contract3);
            contractsDAO.create(contract4);
            optionsDAO.create(option1);
            optionsDAO.create(option2);

            manager.getTransaction().commit();
        }
    }
}