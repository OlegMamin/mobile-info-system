package dao;

import entities.Client;
import entities.Contract;
import entities.Tariff;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by otherz on 06.11.2019.
 */
public class ClientsDAOTest {


    private EntityManagerFactory factory;
    private EntityManager manager;
    private ClientsDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new ClientsDAO(manager);
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
    public void findByLogin() throws Exception {
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

        Client found = dao.findByLogin("test");

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            dao.findByLogin("fakeLogin");
            fail("User fakeLogin shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByLoginAndPassword() throws Exception {
        manager.getTransaction().begin();
        Client client = new Client();

        client.setLogin("test");
        client.setPassword("456");
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

        Client found = dao.findByLoginAndPassword("test", "456");
        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            dao.findByLoginAndPassword("test", "wrongPassword");
            fail("User test has wrong password");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByPassportNumber() throws Exception {
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

        Client found = dao.findByPassportNumber(1987654321);

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            dao.findByPassportNumber(12345);
            fail("User with invalid passportNumber shouldn't be found");
        } catch (NoResultException expected) {

        }

    }
    @Test
    public void findByPhoneNumber() throws Exception {
        manager.getTransaction().begin();
        Client client = new Client();
        client.setLogin("test");
        client.setPassword("123");
        client.setFirstName("Bob");
        client.setLastName("Brown");
        client.setPassportNumber(1987654321);

        Tariff tariff = new Tariff("someTariff", 123);

        Contract contract = new Contract(6767678, client, tariff);

        try {
            manager.persist(client);
            manager.persist(tariff);
            manager.persist(contract);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        Client found = dao.findByPhoneNumber(6767678);

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            dao.findByPhoneNumber(12345);
            fail("User with invalid phone number shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

}