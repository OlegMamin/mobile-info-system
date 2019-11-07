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
public class TariffsDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TariffsDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new TariffsDAO(manager);
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
        Contract contract = new Contract(7557755, client);
        Tariff tariff = new Tariff("tariff", 100, contract);
        Option option = new Option("testDao", 10, 3, contract);


        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(tariff);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Tariff.class, tariff.getId()));
    }

    @Test
    public void findByName() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Contract contract = new Contract(7557755, client);
        Tariff tariff = new Tariff("tariff", 100, contract);
        Option option = new Option("testDao", 10, 3, contract);

        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(tariff);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Tariff found = dao.findByName("tariff");

        Assert.assertNotNull(found);
        Assert.assertEquals(tariff.getId(), found.getId());

        try {
            dao.findByName("fakeName");
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByPriceInterval() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client(1234564145, "login", "123");
        Contract contract = new Contract(7557755, client);
        Tariff tariff = new Tariff("tariff", 100, contract);
        Option option = new Option("testDao", 10, 3, contract);


        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(tariff);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        List<Tariff> found = dao.findByPriceInterval(90, 140);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(tariff.getId(), found.get(0).getId());

    }

}