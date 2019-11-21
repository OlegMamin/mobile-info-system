package dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.dao.ClientsDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.levelup.junior.web.configuration.TestConfig;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

/**
 * Created by otherz on 06.11.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientsDAOTest {

    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private ContractsDAO contractsDAO;

    @Autowired
    private TariffsDAO tariffsDAO;

    @PersistenceContext
    private EntityManager manager;

    private Client client;

    private Contract contract;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "1234");
        clientsDAO.create(client1);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsDAO.create(tariffLow);

        Contract contract1 = new Contract("7557755", client1, tariffLow);
        contractsDAO.create(contract1);

        this.client = client1;
        this.contract = contract1;
    }

    @Test
    public void create() throws Exception {
        Assert.assertNotNull(manager.find(Client.class, client.getId()));
    }

    @Test
    public void findByLogin() throws Exception {

        Client found = clientsDAO.findByLogin(client.getLogin());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            clientsDAO.findByLogin("fakeLogin");
            fail("User fakeLogin shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByLoginAndPassword() throws Exception {

        Client found = clientsDAO.findByLoginAndPassword(client.getLogin(), client.getPassword());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            clientsDAO.findByLoginAndPassword("test", "wrongPassword");
            fail("User test has wrong password");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByPassportNumber() throws Exception {

        Client found = clientsDAO.findByPassportNumber(client.getPassportNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            clientsDAO.findByPassportNumber("12345");
            fail("User with invalid passportNumber shouldn't be found");
        } catch (NoResultException expected) {

        }

    }
    @Test
    public void findByPhoneNumber() throws Exception {

        Client found = clientsDAO.findByPhoneNumber(contract.getPhoneNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

        try {
            clientsDAO.findByPhoneNumber("12345");
            fail("User with invalid phone number shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findById() throws Exception {

        Client found = clientsDAO.findById(client.getId());

        Assert.assertNotNull(found);

        try {
            clientsDAO.findById(-212);
            fail("User with invalid id shouldn't be found");
        } catch (NoResultException expected) {

        }
    }
}