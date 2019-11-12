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
public class ContractsDAOTest {


    private EntityManagerFactory factory;
    private EntityManager manager;
    private ContractsDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new ContractsDAO(manager);
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
        Client client = new Client();

        client.setLogin("test");
        client.setPassword("123");
        client.setFirstName("Bob");
        client.setLastName("Brown");
        client.setPassportNumber(1987654321);



        try {
            manager.persist(client);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Client.class, client.getId()));

    }

    @Test
    public void findByPhoneNumber() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client("John", "Terry",1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3);

        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(contract);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Contract found = dao.findByPhoneNumber(7557755);

        Assert.assertNotNull(found);
        Assert.assertEquals(contract.getId(), found.getId());

        try {
            dao.findByPhoneNumber(1234567);
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByClient() throws Exception {
        manager.getTransaction().begin();

        Client client = new Client("John", "Terry",1234564145, "login", "123");
        Tariff tariff = new Tariff("tariff", 100);
        Contract contract = new Contract(7557755, client, tariff);
        Option option = new Option("testDao", 10, 3);

        try {
            manager.persist(client);
            manager.persist(contract);
            manager.persist(tariff);

            dao.create(contract);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        List<Contract> found = dao.findByClient(client);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(client.getId(), found.get(0).getId());


    }

}