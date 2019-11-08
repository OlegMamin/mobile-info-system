package dao;

import entities.Client;
import entities.Contract;
import entities.Option;
import entities.Tariff;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by otherz on 06.11.2019.
 */
public class OptionsDAOTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private OptionsDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new OptionsDAO(manager);
    }

    @After
    public void cleanUp() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void create() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3, contract);

        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(option);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Option.class, option.getId()));

    }

    @Test
    public void findByName() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3, contract);


        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(option);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Option found = dao.findByName("testDao");

        Assert.assertNotNull(found);
        Assert.assertEquals(option.getId(), found.getId());

        try {
            dao.findByName("fakeName");
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByMonthlyPaymentInterval() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3, contract);

        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(option);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        List<Option> found = dao.findByMonthlyPaymentInterval(1, 10);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());

    }

    @Test
    public void findByCostOfConnectionInterval() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3, contract);


        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(option);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        List<Option> found = dao.findByCostOfConnectionInterval(2, 20);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());

    }

}