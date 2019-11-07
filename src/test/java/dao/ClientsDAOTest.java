package dao;

import entities.Client;
import entities.Option;
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
 * Created by AHYC on 06.11.2019.
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
        client.setName("Bob");
        client.setSurName("Brown");
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
        client.setName("Bob");
        client.setSurName("Brown");
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
        client.setName("Bob");
        client.setSurName("Brown");
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
        client.setName("Bob");
        client.setSurName("Brown");
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


}