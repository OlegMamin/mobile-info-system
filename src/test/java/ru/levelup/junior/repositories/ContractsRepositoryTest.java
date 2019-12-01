package ru.levelup.junior.repositories;

import configuration.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.services.ContractService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by otherz on 30.11.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContractsRepositoryTest {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    @PersistenceContext
    private EntityManager manager;

    private Client client;

    private Contract contract, contractWithoutClientAndTariff;

    private Option option;

    private Tariff tariff;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "12345");
        Client client2 = new Client("Frank", "Lampard"
                , "1005323232", "frog", "4567");
        clientsRepository.save(client1);
        clientsRepository.save(client2);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        Tariff tariffMedium = new Tariff("tariffMedium", 200);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsRepository.save(tariffLow);
        tariffsRepository.save(tariffMedium);
        tariffsRepository.save(tariffHigh);

        Option option1 = new Option("testOption1", 10, 3);
        Option option2 = new Option("testOption2", 12, 4);
        optionsRepository.save(option1);
        optionsRepository.save(option2);

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

        contractsRepository.save(contract1);
        contractsRepository.save(contract2);
        contractsRepository.save(contract3);
        contractsRepository.save(contract4);

        this.contract = contract1;
        this.contractWithoutClientAndTariff = contract4;
        this.client = client1;
        this.option = option1;
        this.tariff = tariffMedium;
    }

    @Test
    public void save() throws Exception {
        Assert.assertNotNull(manager.find(Contract.class, contract.getId()));
    }

    @Test
    public void findById() throws Exception {
        Contract found = contractsRepository.findById(contract.getId()).get();

        Assert.assertNotNull(found);
        Assert.assertEquals(contract.getPhoneNumber(), found.getPhoneNumber());
    }

    @Test
    public void findByPhoneNumber() throws Exception {
        Contract found = contractsRepository.findByPhoneNumber(contract.getPhoneNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(contract.getId(), found.getId());
    }

    @Test
    public void findByClient() throws Exception {
        List<Contract> found = contractsRepository.findByClient(client);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(client.getId(), found.get(0).getClient().getId());
    }

    @Test
    public void findContractsToChose() throws Exception {
        List<Contract> contracts = contractsRepository.findContractsToChose();

        Assert.assertEquals(1, contracts.size());
        Assert.assertEquals("0000000", contracts.get(0).getPhoneNumber());
    }

    @Test
    public void findByClientOrderByPhoneNumber() throws Exception {
        List<Contract> found = contractsRepository.findByClientOrderByPhoneNumber(client);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals("1112233", found.get(0).getPhoneNumber());
        Assert.assertEquals(client.getId(), found.get(0).getClient().getId());

    }

}