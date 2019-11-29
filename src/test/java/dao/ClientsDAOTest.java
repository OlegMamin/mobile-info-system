package dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.*;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Tariff;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import configuration.TestConfig;

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
    private ClientsRepository clientsRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    @PersistenceContext
    private EntityManager manager;

    private Client client;

    private Contract contract;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "1234");
        clientsRepository.save(client1);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsRepository.save(tariffLow);

        Contract contract1 = new Contract("7557755", client1, tariffLow);
        contractsRepository.save(contract1);

        this.client = client1;
        this.contract = contract1;
    }

    @Test
    public void create() throws Exception {
        Assert.assertNotNull(manager.find(Client.class, client.getId()));
    }

    @Test
    public void findByLogin() throws Exception {

        Client found = clientsRepository.findByLogin(client.getLogin());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

    }

    @Test
    public void findByLoginAndPassword() throws Exception {

        Client found = clientsRepository.findByLoginAndPassword(client.getLogin(), client.getPassword());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());
        Assert.assertEquals("John", found.getFirstName());

    }

    @Test
    public void findByPassportNumber() throws Exception {

        Client found = clientsRepository.findByPassportNumber(client.getPassportNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());


    }
    @Test
    public void findByPhoneNumber() throws Exception {

        Client found = clientService.findByPhoneNumber(contract.getPhoneNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());

    }

    @Test
    public void findById() throws Exception {

        Client found = clientsRepository.findById(client.getId()).get();

        Assert.assertNotNull(found);

    }
}