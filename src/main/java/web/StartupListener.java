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
            Client client = new Client("John", "Terry", 1234564145, "test", "123");
            Tariff tariffLow = new Tariff("tariffLow", 100);
            Tariff tariffHigh = new Tariff("tariffHigh", 300);
            Contract contract1 = new Contract(7557755, client, tariffLow);
            Contract contract2 = new Contract(1112233, client, tariffHigh);
            Option option1 = new Option("testDao1", 10, 3, contract1);
            Option option2 = new Option("testDao2", 12, 4, contract2);

            clientsDAO.create(client);
            tariffsDAO.create(tariffHigh);
            tariffsDAO.create(tariffLow);
            contractsDAO.create(contract1);
            contractsDAO.create(contract2);
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
