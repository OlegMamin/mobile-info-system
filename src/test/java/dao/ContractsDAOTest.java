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
import ru.levelup.junior.dao.OptionsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import configuration.TestConfig;

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

    @Autowired
    private OptionsDAO optionsDAO;

    @PersistenceContext
    private EntityManager manager;

    private Client client;

    private Contract contract;

    private Option option;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "12345");
        Client client2 = new Client("Frank", "Lampard"
                , "1005323232", "frog", "4567");
        clientsDAO.create(client1);
        clientsDAO.create(client2);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsDAO.create(tariffLow);
        tariffsDAO.create(tariffHigh);

        Option option1 = new Option("testOption1", 10, 3);
        Option option2 = new Option("testOption2", 12, 4);
        optionsDAO.create(option1);
        optionsDAO.create(option2);

        Contract contract1 = new Contract("7557755");
        Contract contract2 = new Contract("1112233");
        Contract contract3 = new Contract("9994466");
        Contract contract4 = new Contract("0000000");

        contract1.setClient(client1);
        contract2.setClient(client1);
        contract3.setClient(client2);

        contract1.setTariff(tariffHigh);
        contract2.setTariff(tariffLow);
        contract3.setTariff(tariffLow);

        contract1.getOptions().add(option2);
        contract1.getOptions().add(option1);
        contract2.getOptions().add(option2);
        contract2.getOptions().add(option1);
        contract3.getOptions().add(option1);

        contractsDAO.create(contract1);
        contractsDAO.create(contract2);
        contractsDAO.create(contract3);
        contractsDAO.create(contract4);

        this.contract = contract1;
        this.client = client1;
        this.option = option1;
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
            contractsDAO.findByPhoneNumber("1234567");
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByClient() throws Exception {
        List<Contract> found = contractsDAO.findByClient(client);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(client.getId(), found.get(0).getId());
    }

    @Test
    public void findById() throws Exception {
        Contract found = contractsDAO.findById(contract.getId());

        Assert.assertNotNull(found);
        Assert.assertEquals(contract.getPhoneNumber(), found.getPhoneNumber());
    }

    @Test
    public void removeOption() throws Exception {
        contractsDAO.removeOption(contract.getId(), option.getId());
        Contract found = contractsDAO.findById(contract.getId());
        Assert.assertEquals(1, found.getOptions().size());
        Assert.assertEquals("testOption2", found.getOptions().get(0).getName());
    }

    @Test
    public void blockTariff() throws Exception {
        contractsDAO.blockTariff(contract.getId());

        Contract found = contractsDAO.findById(contract.getId());

        Assert.assertNull(found.getTariff());
    }

    @Test
    public void terminateContract() throws Exception {
        contractsDAO.terminateContract(contract.getId());
        Contract found = contractsDAO.findById(contract.getId());

        Assert.assertNull(found.getClient());
    }

    @Test
    public void findContractsToChose() throws Exception {
        List<Contract> contracts = contractsDAO.findContractsToChose();

        Assert.assertEquals(1, contracts.size());
        Assert.assertEquals("0000000", contracts.get(0).getPhoneNumber());
    }

}