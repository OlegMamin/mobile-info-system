package web;

import dao.ClientsDAO;
import dao.ContractsDAO;
import dao.OptionsDAO;
import dao.TariffsDAO;
import entities.Client;
import entities.Contract;
import entities.Option;
import entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Created by otherz on 11.11.2019.
 */
@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        ClientsDAO clientsDAO = new ClientsDAO(manager);
        ContractsDAO contractsDAO = new ContractsDAO(manager);
        TariffsDAO tariffsDAO = new TariffsDAO(manager);
        OptionsDAO optionsDAO = new OptionsDAO(manager);

        try {
            clientsDAO.findByLogin("test");
        } catch (NoResultException notFound) {
            Client client1 = new Client("John", "Terry", 1234564145, "test", "123");
            Client client2 = new Client("Frank", "Lampard", 1005323232, "fr", "456");
            Tariff tariffLow = new Tariff("tariffLow", 100);
            Tariff tariffHigh = new Tariff("tariffHigh", 300);
            Tariff tariffMedium = new Tariff("tariffMedium", 300);
            Contract contract1 = new Contract(7557755, client1, tariffLow);
            Contract contract2 = new Contract(1112233, client1, tariffHigh);
            Contract contract3 = new Contract(2322212, client1, tariffMedium);
            Contract contract4 = new Contract(4666666, client2, tariffHigh);
            Option option1 = new Option("testDao1", 10, 3);
            Option option2 = new Option("testDao2", 12, 4);

            contract1.getOptions().add(option1);
            contract1.getOptions().add(option2);
            contract2.getOptions().add(option1);
            contract3.getOptions().add(option1);
            contract3.getOptions().add(option2);
            contract4.getOptions().add(option2);

            clientsDAO.create(client1);
            clientsDAO.create(client2);
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
        } finally {
            manager.close();
        }
        event.getServletContext().setAttribute("factory", factory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        EntityManagerFactory factory = getFactory(event.getServletContext());

        if (factory != null) {
            factory.close();
        }
    }

    public static EntityManagerFactory getFactory(ServletContext context) {
        return (EntityManagerFactory) context.getAttribute("factory");
    }
}
