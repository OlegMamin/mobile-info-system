package tests;

import ru.levelup.junior.entities.Client;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by otherz on 01.11.2019.
 */
public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testCreateAccount() throws Exception {
        Client client = new Client();
        client.setLogin("test");
        client.setPassword("123");
        client.setFirstName("Bob");
        client.setLastName("Brown");
        client.setPassportNumber(1987654321);


        manager.getTransaction().begin();
        try {
            manager.persist(client);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Client.class, client.getId()));
    }
}
