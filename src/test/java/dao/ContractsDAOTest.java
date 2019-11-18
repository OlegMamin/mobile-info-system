package dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.web.configuration.TestConfig;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by otherz on 06.11.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContractsDAOTest {

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
        Client client1 = new Client("John", "Terry", 1234564145, "test", "12345");
        clientsDAO.create(client1);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsDAO.create(tariffLow);

        Contract contract1 = new Contract(7557755, client1, tariffLow);
        contractsDAO.create(contract1);

        this.client = client1;
        this.contract = contract1;
    }

    @Test
    public void create() throws Exception {
        Assert.assertNotNull(manager.find(Contract.class, contract.getId()));
    }

    @Test
    public void findByPhoneNumber() throws Exception {
        Contract found = contractsDAO.findByPhoneNumber(contract.getPhoneNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(contract.getId(), found.getId());

        try {
            contractsDAO.findByPhoneNumber(1234567);
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByClient() throws Exception {
        List<Contract> found = contractsDAO.findByClient(client);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(client.getId(), found.get(0).getId());
    }

}